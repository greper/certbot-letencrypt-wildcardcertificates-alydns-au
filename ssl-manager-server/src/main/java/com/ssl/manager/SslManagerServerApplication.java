package com.ssl.manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

/**
 * @author xiaojunnuo
 */
@SpringBootApplication
@MapperScan("com.ssl.manager.**.mapper")
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SslManagerServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SslManagerServerApplication.class, args);
    }

}
