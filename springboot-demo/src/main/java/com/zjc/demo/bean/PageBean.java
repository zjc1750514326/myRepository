package com.zjc.demo.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/8 15:02
 */
@Data
@ApiModel
public class PageBean {
    @ApiModelProperty("页数")
    Integer pageIndex = 1;
    @ApiModelProperty("页大小")
    Integer pageSize = 10;
}
