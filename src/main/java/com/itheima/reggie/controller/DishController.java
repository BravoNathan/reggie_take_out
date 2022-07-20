package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.Dto.PageDish;
import com.itheima.reggie.common.Result;
import com.itheima.reggie.entity.mysql.Dish;
import com.itheima.reggie.entity.mysql.DishFlavor;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/19
 */
@RestController
@Slf4j
@RequestMapping("/dish")
@Api(tags = "菜品管理")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @ApiOperation(value = "分页查询展示")
    @GetMapping("/page")
    public Result<IPage<Dish>> page(PageDish pageDish){
        log.info("菜品分页查询page:{}, pageSize:{}, name:{}", pageDish.getPage(), pageDish.getPageSize(),pageDish.getName());
        Page<Dish> page = new Page<>(pageDish.getPage(), pageDish.getPageSize());
        IPage<Dish> dishIPage= dishService.page(page, new LambdaQueryWrapper<Dish>()
                .like(StringUtils.hasText(pageDish.getName()) ,Dish::getName, pageDish.getName())
                .orderByAsc(Dish::getId));
        return Result.success("菜品分页查询成功", dishIPage);
    }
}
