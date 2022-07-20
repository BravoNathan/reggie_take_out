package com.itheima.reggie.entity.mysql;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "菜品/套餐分类")
public class Category extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 11L;

    @ApiModelProperty(value = "菜品分类/套餐分类id", required = true )
    private Long id;

    @ApiModelProperty(value = "菜品分类/套餐分类", required = true)
    private Integer type;

    @ApiModelProperty(value = "菜品/套餐名字")
    private String name;

    @ApiModelProperty(value = "顺序")
    private Integer sort;



}
