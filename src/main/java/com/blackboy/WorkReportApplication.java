package com.blackboy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class WorkReportApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkReportApplication.class, args);
    }

}
