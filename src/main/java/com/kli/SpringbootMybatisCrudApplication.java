package com.kli;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class SpringbootMybatisCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisCrudApplication.class, args);
    }

}
