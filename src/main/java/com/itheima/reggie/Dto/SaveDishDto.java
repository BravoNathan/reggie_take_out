package com.itheima.reggie.Dto;

import com.itheima.reggie.entity.mysql.Dish;
import com.itheima.reggie.entity.mysql.DishFlavor;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/21
 */
@Data
public class SaveDishDto extends Dish {

    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;


}
