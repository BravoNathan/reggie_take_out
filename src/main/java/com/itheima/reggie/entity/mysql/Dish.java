package com.itheima.reggie.entity.mysql;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@ApiModel(value = "菜品")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 21L;

    @ApiModelProperty(value = "菜品id")
    private Long id;

    //菜品名称
    @ApiModelProperty(value = "菜品名称")
    private String name;

    //菜品分类id
    @ApiModelProperty(value = "菜品类别id")
    private Long categoryId;

    //菜品价格
    @ApiModelProperty(value = "菜品价格")
    private BigDecimal price;

    //商品码
    @ApiModelProperty(value = "商品码")
    private String code;

    //图片
    @ApiModelProperty(value = "图片")
    private String image;

    //描述信息
    @ApiModelProperty(value = "描述信息")
    private String description;

    //0 停售 1 起售
    @ApiModelProperty(value = "0 停售 1 起售")
    private Integer status;

    //顺序
    @ApiModelProperty(value = "顺序")
    private Integer sort;

    //是否删除
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

}
