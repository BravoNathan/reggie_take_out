package com.itheima.reggie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.Dto.PageDishDto;
import com.itheima.reggie.Dto.SaveDishDto;
import com.itheima.reggie.entity.mysql.Dish;

public interface DishService extends IService<Dish> {

    public IPage<PageDishDto> pageDish(PageDishDto pageDishDto);

    public void saveDish(SaveDishDto dto);

    public void deleteDish(Long id);
}
