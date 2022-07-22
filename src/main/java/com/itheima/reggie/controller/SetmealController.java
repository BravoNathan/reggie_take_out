package com.itheima.reggie.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itheima.reggie.Dto.PageSetmealDto;
import com.itheima.reggie.Dto.SetmealSaveDto;
import com.itheima.reggie.common.Result;
import com.itheima.reggie.entity.mysql.Setmeal;
import com.itheima.reggie.service.SetmealService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/22
 */

@RestController
@Slf4j
@Api(tags = "套餐管理")
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;


    @GetMapping("/page")
    public Result<IPage<PageSetmealDto>> page(PageSetmealDto dto){
        return Result.success("套餐列表展示成功", setmealService.getPage(dto));
    }

    @DeleteMapping
    public Result<String> deleteSet(@RequestParam List<Long> id){
        setmealService.removeByIds(id);
        return Result.success("删除成功");
    }

    @PostMapping("/status/{status}")
    public Result<String> updateStatus(@PathVariable int status, @RequestParam List<Long> ids){
        setmealService.setStatus(status, ids);
        return Result.success("状态更改成功");
    }

    @PostMapping
    public Result<String> saveSetmeal(@RequestBody SetmealSaveDto setmealSaveDto){
        setmealService.saveSetmeal(setmealSaveDto);
        return Result.success("新增套餐成功");
    }
}
