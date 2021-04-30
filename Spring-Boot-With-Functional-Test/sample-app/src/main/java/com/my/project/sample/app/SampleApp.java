package com.my.project.sample.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "com.my.project.sample.app")
@EnableSwagger2
public class SampleApp {
    public static void main(String[] args) {
        SpringApplication.run(SampleApp.class);
    }
}
