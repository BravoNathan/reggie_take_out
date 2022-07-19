package com.itheima.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.jfr.DataAmount;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "菜品口味")
public class DishFlavor implements Serializable {
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
