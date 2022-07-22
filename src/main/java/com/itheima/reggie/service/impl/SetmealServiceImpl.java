package com.itheima.reggie.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.Dto.PageSetmealDto;
import com.itheima.reggie.Dto.SetmealDishes;
import com.itheima.reggie.Dto.SetmealSaveDto;
import com.itheima.reggie.entity.mysql.Category;
import com.itheima.reggie.entity.mysql.Setmeal;
import com.itheima.reggie.entity.mysql.SetmealDish;
import com.itheima.reggie.mapper.SetmealMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.SetmealDishService;
import com.itheima.reggie.service.SetmealService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    @Lazy
    private CategoryService categoryService;

    @Override
    public IPage<PageSetmealDto> getPage(PageSetmealDto dto) {

        Page<Setmeal> pageInfo = new Page(dto.getPage(), dto.getPageSize());
        Page<PageSetmealDto> page = new Page<>();

        LambdaQueryWrapper<Setmeal> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(dto.getName()), Setmeal::getName, dto.getName())
                .orderByDesc(Setmeal::getUpdateTime);
        this.page(pageInfo, wrapper);
        BeanUtils.copyProperties(pageInfo, page, "records");
        List<Setmeal> records = pageInfo.getRecords();

        List<PageSetmealDto> list = records.stream().map(item ->{
            PageSetmealDto pageSetmealDto = new PageSetmealDto();
            BeanUtils.copyProperties(item, pageSetmealDto);
            Category category = categoryService.getById(item.getCategoryId());
            pageSetmealDto.setCategoryName(category.getName());
            return pageSetmealDto;
        }).collect(Collectors.toList());

        page.setRecords(list);
        return page;



    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void setStatus(int status, List<Long> ids) {
        List<Setmeal> setmeals = ids.stream().map(id ->{
            Setmeal setmeal = this.getById(id);
            setmeal.setStatus(status);
            return setmeal;
        }).collect(Collectors.toList());

        this.updateBatchById(setmeals);

        log.info("状态转换完成{}", setmeals);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveSetmeal(SetmealSaveDto setmealSaveDto) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealSaveDto, setmeal);
        log.info("正在存储setmeal");
        this.save(setmeal);
        List setmealDishedList = new ArrayList<>();
        for(SetmealDishes setmealDishes: setmealSaveDto.getSetmealDishes()){
            SetmealDish setmealDish = new SetmealDish();
            setmealDish.setSetmealId(setmeal.getId());
            setmealDish.setDishId(setmealDishes.getDishId());
            setmealDish.setCopies(setmealDishes.getCopies());
            setmealDish.setName(setmealDishes.getName());
            setmealDish.setPrice(setmealDishes.getPrice());
            setmealDishedList.add(setmealDish);
        }
        log.info("正在存储setmeal");
        setmealDishService.saveBatch(setmealDishedList);
    }
}
