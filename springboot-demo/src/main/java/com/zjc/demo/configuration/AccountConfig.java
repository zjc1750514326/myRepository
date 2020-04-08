package com.zjc.demo.configuration;

import com.zjc.demo.entity.Account;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/1 11:14
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "system.config")
public class AccountConfig {

    private List<Account> accounts;
    private Map<String,String> map;
    private Integer port;
    /** 随机数 */
    private Integer randomNum;
}
