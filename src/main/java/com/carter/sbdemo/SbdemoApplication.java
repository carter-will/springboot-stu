package com.carter.sbdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication                 //(exclude = {DataSourceAutoConfiguration.class})  如果乜有数据源，但又引入了mybatis包，需要exclude
@ServletComponentScan                  // 启动类必须加入@ServletComponentScan注解，否则无法扫描到servlet
//@EnableCaching                         // @EnableCaching必须要加，否则spring-data-cache相关注解不会生效
@EnableAsync                           //支持异步调用
@EnableScheduling                      //加入@EnableScheduling注解，启用定时任务的配置
public class SbdemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SbdemoApplication.class, args);
    }
}
