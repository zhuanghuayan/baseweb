package com.springapp.mvc.entity;

import java.util.List;

/**
 * Created by wuqinghai on 16/10/23.
 */
public class SoftList {

    private String count;

    public List<APKEntity> getList() {
        return list;
    }

    public void setList(List<APKEntity> list) {
        this.list = list;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    private List<APKEntity> list;
}
