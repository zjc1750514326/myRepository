package com.zjc.demo.vo;

import lombok.Data;

import java.util.List;

@Data
public class PageRecords<T> {
    Integer total = 0;
    Integer index = 1;
    Integer size = 10;
    List<T> records = null;
    public PageRecords(Integer pageindex,Integer pagesize)
    {
        this.setIndex(pageindex);
        this.setSize(pagesize);
    }
}
