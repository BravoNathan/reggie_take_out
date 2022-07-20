package com.itheima.reggie.Dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/20
 */
@ApiModel(value = "分页查询DTO")
@Data
public class PageDto {
    @ApiModelProperty(value = "页数")
    private Integer page;

    @ApiModelProperty(value = "每页大小")
    private Integer pageSize;
}
