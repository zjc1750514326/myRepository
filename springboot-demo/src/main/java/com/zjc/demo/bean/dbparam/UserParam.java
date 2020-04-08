package com.zjc.demo.bean.dbparam;

import com.zjc.demo.bean.PageBean;
import com.zjc.demo.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/8 16:44
 */
@Data
@ApiModel
public class UserParam extends PageBean {

    @ApiModelProperty(value = "起始的行",hidden = true)
    private Integer startIndex = 1;
    @ApiModelProperty(value = "查询的行数",hidden = true)
    private Integer lineSize = 10;

    @ApiModelProperty("用户id")
    private Long id;

    @ApiModelProperty("用户账号")
    private String account;

    @ApiModelProperty("用户密码")
    private String password;

    @ApiModelProperty("用户邮箱")
    private String email;


    public UserParam(Integer startIndex,Integer lineSize){
        this.startIndex = startIndex;
        this.lineSize = lineSize;
    }
}
