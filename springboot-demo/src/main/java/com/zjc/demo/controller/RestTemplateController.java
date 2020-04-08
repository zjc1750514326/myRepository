package com.zjc.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author - zjc
 * @Description -
 * @weixin - ZJC1750514326
 * @email - 1750514326@qq.com
 * @date - 2020/4/7 9:15
 */
@RestController
@Slf4j
@RequestMapping("restTemplate")
@Api(tags = "RestTemplate Http请求管理层")
public class RestTemplateController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("hello1")
    @ApiOperation("getForEntity请求方法1")
    public String hello1(){
        String name = "zjc";
        String url = "http://127.0.0.1:9001/restTempTest/hello?name={1}";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, name);

        StringBuffer sb = new StringBuffer();
        HttpStatus statusCode = responseEntity.getStatusCode();
        String body = responseEntity.getBody();
        sb.append("statusCode：")
                .append(statusCode)
                .append("</br>")
                .append("body：")
                .append(body)
                .append("</br>");
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }
        return sb.toString();
    }


    @GetMapping("hello2")
    @ApiOperation("getForEntity请求方法2")
    public String hello2(){
        String name = "zjc";
        String url = "http://127.0.0.1:9001/restTempTest/hello?name={name}";
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class, map);

        StringBuffer sb = new StringBuffer();
        HttpStatus statusCode = responseEntity.getStatusCode();
        String body = responseEntity.getBody();
        sb.append("statusCode：")
                .append(statusCode)
                .append("</br>")
                .append("body：")
                .append(body)
                .append("</br>");
        HttpHeaders headers = responseEntity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String s : keySet) {
            sb.append(s)
                    .append(":")
                    .append(headers.get(s))
                    .append("</br>");
        }
        return sb.toString();
    }


    @GetMapping("helloHeader")
    @ApiOperation("设置请求头")
    public String helloHeader(){

        String url = "http://127.0.0.1:9001/restTempTest/helloHeader?name=zjc";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("token","123456789");
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class);

        log.info("getStatusCode: "+response.getStatusCode());
        log.info("getStatusCode: "+response.getBody());

        return response.getBody();
    }

    @PostMapping("helloHeaderPost")
    @ApiOperation("设置请求头,POST")
    public String helloHeaderPost(){

        String url = "http://127.0.0.1:9001/restTempTest/helloHeaderPost";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("token","123456789");

        //body
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("name", "我是POST");

        HttpEntity<MultiValueMap> httpEntity = new HttpEntity<>(requestBody,httpHeaders);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, httpEntity, String.class);


        log.info("getStatusCode: "+response.getStatusCode());
        log.info("getStatusCode: "+response.getBody());

        return response.getBody();
    }
}
