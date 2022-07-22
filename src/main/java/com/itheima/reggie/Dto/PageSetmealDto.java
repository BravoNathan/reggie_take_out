package com.itheima.reggie.Dto;

import com.itheima.reggie.entity.mysql.Setmeal;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/22
 */
@Data
public class PageSetmealDto extends Setmeal {

    @ApiModelProperty("套餐名称")
    private String categoryName;

    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
}
