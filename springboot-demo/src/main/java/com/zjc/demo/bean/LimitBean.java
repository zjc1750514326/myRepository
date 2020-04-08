package com.zjc.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author - zjc
 * @Description - 分页查询起始和结束bean
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/8 16:35
 */
@Data
@ApiModel
public class LimitBean {

    @ApiModelProperty("起始的行")
    private Integer startIndex;
    @ApiModelProperty("查询的行数")
    private Integer lineSize;

    public LimitBean(Integer startIndex,Integer lineSize){
        this.startIndex = startIndex;
        this.lineSize = lineSize;
    }
}
