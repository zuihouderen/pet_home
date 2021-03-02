package com.example.webpet;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//扫描mapper组件
@MapperScan("com.example.webpet.mapper")
public class WebPetApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebPetApplication.class, args);
    }

}
