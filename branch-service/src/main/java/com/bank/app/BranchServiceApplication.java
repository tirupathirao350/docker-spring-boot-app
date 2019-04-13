package com.bank.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class BranchServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BranchServiceApplication.class, args);
    }

}

