package com.itheima.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@ApiModel(value = "菜品")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dish implements Serializable {

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
    @ApiModelProperty(value = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @ApiModelProperty(value = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @ApiModelProperty(value = "更新人")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    //是否删除
    @ApiModelProperty(value = "是否删除")
    private Integer isDeleted;

}
