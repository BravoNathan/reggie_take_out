package com.itheima.reggie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.Dto.PageDishDto;
import com.itheima.reggie.Dto.SaveDishDto;
import com.itheima.reggie.entity.mysql.Dish;

import java.util.List;

public interface DishService extends IService<Dish> {

    IPage<PageDishDto> pageDish(PageDishDto pageDishDto);

    void saveDish(SaveDishDto dto);

    void deleteDish(List<Long> id);

    void setStatus(int status, List<Long> id);

    List<Dish> getCategoryDish(Long categoryId);
}
