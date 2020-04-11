package com.zjc.demo.controller;

import com.zjc.demo.service.IMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/9 14:46
 */
@RestController
@Slf4j
@RequestMapping("email")
@Api(tags = "邮件发送测试")
public class SendEmailController {

    /**
     * 注入发送邮件的接口
     */
    @Autowired
    private IMailService mailService;

    /**
     * 测试发送文本邮件
     */
    @ApiOperation("发送163邮件")
    @GetMapping("sendmail")
    public void sendmail() {
        mailService.sendSimpleMail("17606050819@163.com","主题：你好普通邮件","内容：第一封邮件");
    }

    @ApiOperation("发送qq邮件")
    @GetMapping("sendmailHtml")
    public void sendmailHtml(){
        mailService.sendHtmlMail("1750514326@qq.com","主题：你好html邮件","<h1>内容：第一封html邮件</h1>");
    }

    @ApiOperation("发送公司邮件")
    @GetMapping("sendmailHtmlComp")
    public void sendmailHtmlComp(){
        mailService.sendHtmlMail("jincheng.zhang@unionman.com.cn","主题：你好html邮件","<h1>内容：第一封html邮件</h1>");
    }
}
