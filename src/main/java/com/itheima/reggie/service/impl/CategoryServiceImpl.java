package com.itheima.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.reggie.common.CustomException;
import com.itheima.reggie.entity.mysql.Category;
import com.itheima.reggie.entity.mysql.Dish;
import com.itheima.reggie.entity.mysql.Setmeal;
import com.itheima.reggie.mapper.CategoryMapper;
import com.itheima.reggie.service.CategoryService;
import com.itheima.reggie.service.DishService;
import com.itheima.reggie.service.SetmealService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class CategoryServiceImpl  extends ServiceImpl<CategoryMapper,Category> implements CategoryService {

    @Resource
    private DishService dishService;

    @Resource
    private SetmealService setmealService;

    /**
     * 根据ID删除分类，删除之前需要判断是否关联菜品或套餐
     * @param id
     */
    @Override
    public void remote(Long id) {

        LambdaQueryWrapper<Dish> wrapperDish = new LambdaQueryWrapper<>();
        wrapperDish.eq(Dish::getCategoryId, id);
        int countDish = dishService.count(wrapperDish);
        if(countDish > 0){
            throw new CustomException("当前分类已关联菜品，不可删除");
        }
        LambdaQueryWrapper<Setmeal> wrapperSetmeal = new LambdaQueryWrapper<>();
        wrapperSetmeal.eq(Setmeal::getCategoryId, id);
        int countSetmeal = setmealService.count(wrapperSetmeal);
        if(countSetmeal > 0){
            throw new CustomException("当前分类已关联套餐，不可删除");
        }
        super.removeById(id);

    }
}
