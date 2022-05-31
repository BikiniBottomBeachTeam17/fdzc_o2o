package com.mustar;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.mustar.mapper")
public class YcxyApplication {
    public static void main(String[] args) {
        SpringApplication.run(YcxyApplication.class, args);
    }

}
