package com.itheima.reggie.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Dto.PageDishDto;
import com.itheima.reggie.Dto.SaveDishDto;
import com.itheima.reggie.entity.mysql.Category;
import com.itheima.reggie.entity.mysql.Dish;
import com.itheima.reggie.entity.mysql.DishFlavor;
import com.itheima.reggie.mapper.DishMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish> implements DishService {

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Override
    public IPage<PageDishDto> pageDish(PageDishDto pageDishDto) {
        log.info("菜品分页查询page:{}, pageSize:{}, name:{}", pageDishDto.getPage(), pageDishDto.getPageSize(), pageDishDto.getName());
        // 因为我们需要的是PageDishDto的分页数据，但是数据库只有Dish这张表，所以通过对Dish做Page，再把其中的属性赋值给PageDishDto
        Page<Dish> pageInfo = new Page<>(pageDishDto.getPage(), pageDishDto.getPageSize());
        IPage<PageDishDto> dishPage = new Page<>();

        // 现在需要一个查询语句
        LambdaQueryWrapper<Dish> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasText(pageDishDto.getName()), Dish::getName, pageDishDto.getName())
                .orderByDesc(Dish::getUpdateTime);
        page(pageInfo, wrapper);
        BeanUtils.copyProperties(pageInfo,dishPage,"records");
        log.info("copy pageInfo信息至dishPage");
        List<PageDishDto> list = pageInfo.getRecords().stream()
                .map(k -> {
                    PageDishDto dto = new PageDishDto();
                    log.info("通过id查询category");
                    Category category = categoryService.getById(k.getCategoryId());
                    BeanUtils.copyProperties(k,dto);
                    if(!Objects.equals(category, null)) {
                        dto.setCategoryName(categoryService.getById(k.getCategoryId()).getName());
                    }
                    return dto;
                })
                .collect(Collectors.toList());
        dishPage.setRecords(list);
        return dishPage;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveDish(SaveDishDto dto) {
        log.info("开始存储新增菜品{}",dto);
        this.save(dto);
        Long dishId = dto.getId();
        List<DishFlavor> flavors = dto.getFlavors();
        flavors = flavors.stream()
                .map(item -> {
                    item.setDishId(dishId);
                    return item;
                })
                .collect(Collectors.toList());
        dishFlavorService.saveBatch(flavors);
        log.info("新增菜品成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDish(Long id) {
        log.info("正在删除dish：{}",id);
        Dish dish = getById(id);
        dish.setIsDeleted(1);
        LambdaQueryWrapper<DishFlavor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DishFlavor::getDishId, id);
        List<DishFlavor> dishFlavors = dishFlavorService.list(wrapper);
        if(CollUtil.isNotEmpty(dishFlavors)){
            dishFlavors.stream().map(flavor -> {
                flavor.setIsDeleted(1);
                return flavor;
            }).collect(Collectors.toList());
        }
        dishFlavorService.updateBatchById(dishFlavors);
        this.updateById(dish);
    }



}
