package com.github.yeecode.mybatisdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 通过mybatis-spring-boot-starter整合了MyBatis的Spring Boot后端项目示例。
 */
@SpringBootApplication
public class MybatisdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisdemoApplication.class, args);
        System.out.println("Via http://127.0.0.1:8099/ to trigger this demo.");
    }

}
