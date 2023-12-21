package com.example.wb_api_parising;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.example"})
public class WpApiParsingApplication {

    public static void main(String[] args) {
        SpringApplication.run(WpApiParsingApplication.class, args);
    }

}
