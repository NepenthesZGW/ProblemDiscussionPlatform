package com.zgw.servicefile;

import com.zgw.pdpcommon.util.LogUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;


@EnableDiscoveryClient
@SpringBootApplication
public class ServiceFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceFileApplication.class, args);
//        LogUtil.getAsyncLogger(ServiceFileApplication.class).info("好累啊");
//        LogUtil.getErrorLogger().error("调用工具类");
//        LogUtil.getWarnLogger().warn("调用工具类");
//        String name="log4j2.xml";
//
//        Enumeration<URL> resources = LogUtil.class.getClassLoader().getResources(name);
//
//        while (resources.hasMoreElements()){
//            URL url = resources.nextElement();
//            String file = url.getFile();
//
//            System.out.println(file);
//
//        }
    }

}
