package com.itheima.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.reggie.entity.mysql.Category;

public interface CategoryService extends IService<Category> {

    public void remote(Long id);
}
