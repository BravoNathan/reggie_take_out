package com.itheima.reggie.Dto;

import com.itheima.reggie.entity.mysql.Dish;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/20
 */
@Data
@ApiModel(value = "菜品分页")
public class PageDishDto extends Dish {

    @ApiModelProperty(value = "分类名字")
    private String categoryName;

    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
}

