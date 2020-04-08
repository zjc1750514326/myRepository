package com.zjc.demo.controller;

import com.zjc.demo.configuration.AccountConfig;
import com.zjc.demo.entity.Account;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 11:17
 */
@RestController
@Slf4j
@RequestMapping("hello")
@Api(tags = "配置测试管理层")
public class HelloController {

    @Autowired
    private AccountConfig accountConfig;

    @ApiOperation("获取集合配置")
    @GetMapping("getAccounts")
    public List<Account> getAccounts(){
        return accountConfig.getAccounts();
    }

    @ApiOperation("获取Map配置")
    @GetMapping("getMap")
    public Map<String,String> getMap(){
        return accountConfig.getMap();
    }

    @ApiOperation("获取占位符配置")
    @GetMapping("getPort")
    public Integer getPort(){
        return accountConfig.getPort();
    }

    /**
     * 随机生成uuid字符串
     */
    @Value("${random.uuid}")
    private String uuid;
    /**
     * 随机生成0~1000的正整数
     */
    @Value("${random.int(1000)}")
    private int maxInt;
    /**
     * 随机生成0~102400的long类型数值
     */
    @Value("${random.long(102400)}")
    private long maxLong;

    @ApiOperation("获取随机数配置")
    @GetMapping("getRandomNum")
    public Integer getRandomNum(){

        log.info("uuid: "+uuid);
        log.info("maxInt: "+maxInt);
        log.info("maxLong: "+maxLong);

        return accountConfig.getRandomNum();
    }
}
