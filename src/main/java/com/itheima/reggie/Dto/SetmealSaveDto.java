package com.itheima.reggie.Dto;

import com.itheima.reggie.entity.mysql.Setmeal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/22
 */
@Data
public class SetmealSaveDto{

    private List<SetmealDishes> setmealDishes;

    @ApiModelProperty(value = "分类id")
    private Long categoryId;

    //分类id
    @ApiModelProperty(value = "分类id")
    private String name;

    //套餐价格
    @ApiModelProperty(value = "套餐价格")
    private BigDecimal price;

    //状态 0:停用 1:启用
    @ApiModelProperty(value = "状态 0:停用 1:启用")
    private Integer status;

    //编码
    @ApiModelProperty(value = "编码")
    private String code;

    //描述信息
    @ApiModelProperty(value = "描述信息")
    private String description;

    //图片
    @ApiModelProperty(value = "图片")
    private String image;

}
