package com.itheima.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.java.Log;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @Author: nathan
 * @Date: 2022/7/15
 */
public class MyMetaObjectHandler implements MetaObjectHandler {
    public static final Logger LOGGER = LoggerFactory.getLogger(MyMetaObjectHandler.class);


    @Override
    public void insertFill(MetaObject metaObject) {
        LOGGER.info("公共字段自动填充中[insert]...");
        LOGGER.info(metaObject.toString());
        metaObject.setValue("createTime", LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser", BaseContext.getCurrentId());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());


    }

    @Override
    public void updateFill(MetaObject metaObject) {
        LOGGER.info("公共字段自动填充中[update]...");
        LOGGER.info(metaObject.toString());

        long id = Thread.currentThread().getId();
        LOGGER.info("线程id为{}",id);

        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("updateUser", BaseContext.getCurrentId());



    }
}
