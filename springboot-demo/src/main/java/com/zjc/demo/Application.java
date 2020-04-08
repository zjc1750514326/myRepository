package com.zjc.demo;

import com.zjc.demo.listener.ApplicationStartedEventListener;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@MapperScan("com.zjc.demo.dao")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

        // 手动实例化SpringApplication方式
       /* SpringApplication application = new SpringApplication(Application.class);
        // 添加注册监听器
        application.addListeners(new ApplicationStartedEventListener());
        // 启动应用程序
        application.run(args);*/
    }

    @Bean
    public RestTemplate getRestTemplate(){

        return new RestTemplate();
    }

}
