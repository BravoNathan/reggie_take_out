package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.common.Result;
import com.itheima.reggie.entity.Category;
import com.itheima.reggie.service.CategoryService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@Api(tags = "菜品分类管理")
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    /**
     * 新增分类
     * @param category
     * @return
     */
    @PostMapping
    public Result<String> save(@RequestBody Category category){
        log.info("category:{}",category);
        categoryService.save(category);
        return Result.success("新增分类成功");
    }

    @GetMapping("/page")
    public Result<Page> page(int page, int pageSize){
        log.info("展示菜品/套餐分类");
        Page<Category> pageInfo = new Page<>(page, pageSize);

        LambdaQueryWrapper<Category> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByAsc(Category::getSort);

        categoryService.page(pageInfo, wrapper);
        return Result.success(pageInfo);
    }

    @PutMapping
    public Result<String> update(@RequestBody Category category){
        log.info("更新菜品分类{}",category);
        categoryService.updateById(category);
        return Result.success("菜品分类修改成功");

    }


}
