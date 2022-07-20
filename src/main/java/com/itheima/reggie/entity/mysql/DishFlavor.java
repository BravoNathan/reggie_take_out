package com.itheima.reggie.entity.mysql;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "菜品口味")
public class DishFlavor extends BaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "口味id")
    private Long id;

    //菜品id
    @ApiModelProperty(value = "菜品id")
    private Long dishId;

    //口味名称
    @ApiModelProperty(value = "口味名称")
    private String name;

    //口味数据list
    @ApiModelProperty(value = "口味数据list")
    private String value;

    //是否删除
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;
}
