package com.itheima.reggie.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.Dto.PageSetmealDto;
import com.itheima.reggie.Dto.SetmealSaveDto;
import com.itheima.reggie.entity.mysql.Setmeal;

import java.util.List;

public interface SetmealService extends IService<Setmeal> {

    IPage<PageSetmealDto> getPage(PageSetmealDto pageSetmealDto);

    void setStatus(int status, List<Long> ids);

    void saveSetmeal(SetmealSaveDto setmealSaveDto);


}
