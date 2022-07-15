package com.itheima.reggie.common;

/**
 * @Author: nathan
 * @Date: 2022/7/15
 */
public class BaseContext {

    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();


    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }



}
