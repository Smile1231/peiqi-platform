package com.sap.peiqiplatform;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sap.peiqiplatform.mapper")
public class PeiqiPlantformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PeiqiPlantformApplication.class, args);
    }

}
