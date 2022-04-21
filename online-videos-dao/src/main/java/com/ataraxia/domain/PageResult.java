package com.ataraxia.domain;

import java.util.List;

/**
 * @author Ataraxia
 * @create 2022/4/21 21:22
 * @description 分页信息
 */
public class PageResult<T> {

    /**
     * 查询结果总数
     */
    private Long total;

    /**
     * 当前分页列表
     */
    private List<T> list;

    public PageResult(Long total, List<T> list) {
        this.total = total;
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
