package com.neighbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.neighbor.restservice", "com.neighbor.graphql.query"})
public class NeighborwebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeighborwebsiteApplication.class, args);
    }

}
