package com.itheima.reggie.common;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.reggie.Dto.PageDto;

/**
 * @Author: zhengyang.li
 * @Date: 2022/7/20
 */
public class PageFactory {
    /**
     * 每页显示条数，默认 20
     */
    private static final long SIZE_VALUE = 20;

    /**
     * 当前页
     */
    private static final long CURRENT_VALUE = 1;

    /**
     * 获取page实例
     */
    public static <T> Page<T> getInstance(PageDto dto) {
        long newCurrent = dto.getPage() == null ? CURRENT_VALUE : dto.getPage();
        long newSize = dto.getPageSize() == null ? SIZE_VALUE : dto.getPageSize();
        return new Page<>(newCurrent, newSize);
    }
}
