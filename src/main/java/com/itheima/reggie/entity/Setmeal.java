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

@Data
@ApiModel(value = "套餐")
@AllArgsConstructor
@NoArgsConstructor
public class Setmeal implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "套餐id", required = true)
    private Long id;

    //分类id
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
