package com.itheima.reggie.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/20
 */
@Data
@ApiModel(value = "菜品分页")
public class PageDish extends PageDto{

    @ApiModelProperty(value = "菜品名字" )
    private String name;
}

