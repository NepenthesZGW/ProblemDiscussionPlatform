package com.zgw.registryone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@EnableEurekaServer  //使用注解启动eureka服务
@SpringBootApplication
public class RegistryOneApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegistryOneApplication.class, args);
    }

}
