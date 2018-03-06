package com.intive.shopme;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ShopMeApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShopMeApplication.class, args);
    }

}
