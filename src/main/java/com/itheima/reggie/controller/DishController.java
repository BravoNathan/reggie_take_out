package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.reggie.Dto.PageDishDto;
import com.itheima.reggie.Dto.SaveDishDto;
import com.itheima.reggie.common.Result;
import com.itheima.reggie.entity.mysql.Dish;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishFlavorService;
import com.itheima.reggie.service.DishService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/19
 */
@RestController
@Slf4j
@RequestMapping("/dish")
@Validated
@Api(tags = "菜品管理")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private DishFlavorService dishFlavorService;

    @Autowired
    private CategoryService categoryService;

    @ApiOperation(value = "分页查询展示")
    @GetMapping("/page")
    public Result<IPage<PageDishDto>> page(PageDishDto pageDishDto){
        return Result.success("菜品分页查询成功",dishService.pageDish(pageDishDto));
    }

    @ApiOperation(value = "新增菜品")
    @PostMapping
    public Result<String> saveDish(@RequestBody SaveDishDto dto){
        dishService.saveDish(dto);
        return Result.success("新增菜品成功");
    }

    @DeleteMapping
    public Result<String> deleteDish(@RequestParam("ids") @NotNull(message = "dish id不能为空") List<Long> id){
        dishService.deleteDish(id);
        return Result.success("删除成功");
    }

    @PostMapping("/status/{status}")
    public Result<String> setStatus(@PathVariable("status") int status, @RequestParam List<Long> ids){
        dishService.setStatus(status,ids);
        return Result.success("修改状态成功");
    }

    @GetMapping("/list")
    public Result<List<Dish>> getDish(@RequestParam("categoryId") Long categoryId){
        return Result.success("查询成功",dishService.getCategoryDish(categoryId));
    }






}
