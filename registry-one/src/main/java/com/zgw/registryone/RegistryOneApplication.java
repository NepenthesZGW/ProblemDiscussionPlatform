package com.zgw.registryone;

import com.zgw.registryone.config.MyFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;


@EnableEurekaServer  //使用注解启动eureka服务
@SpringBootApplication
public class RegistryOneApplication {

    public static void main(String[] args) {

        SpringApplication.run(RegistryOneApplication.class, args);
    }

    // 具体实现
    @Bean
    public FilterRegistrationBean<MyFilter> filterRegistrationBean() {
        FilterRegistrationBean<MyFilter> registration = new FilterRegistrationBean<MyFilter>(new MyFilter());  //注意这里
        registration.addUrlPatterns("/*");
        return registration;
    }

}
