package com.itheima.reggie.Dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/22
 */
@Data
public class SetmealDishes {

    private Integer copies;

    private Long dishId;

    private String name;

    private BigDecimal price;

}
