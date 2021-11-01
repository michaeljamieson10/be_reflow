package com.neighbor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan(basePackages = {
//        "com.neighbor.*"
//})
//@EntityScan("com.neighbor.*")
//@EnableJpaRepositories("com.neighbor.*")
@ComponentScan(lazyInit = true)
public class NeighborwebsiteApplication {

    public static void main(String[] args) {
        SpringApplication.run(NeighborwebsiteApplication.class, args);
    }

}
