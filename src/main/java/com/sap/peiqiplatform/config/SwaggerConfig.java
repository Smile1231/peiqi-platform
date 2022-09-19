package com.sap.peiqiplatform.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author jinMao
 * @version 1.0.0
 * @ClassName SwaggerConfig.java
 * @Description TODO
 * @createTime 2022-08-25  18:51:00
 */
@Configuration//加载到springboot配置里面
@EnableSwagger2//开启swagger2
public class SwaggerConfig {
    @Value("${spring.profiles.active:NA}")
    private String active;

    @Bean
    public Docket docket() {
        return new Docket(DocumentationType.SWAGGER_2)// OAS_30
                .enable("local".equals(active))
                .groupName("Peiqi")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.sap.peiqiplatform.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API Document")
                .description("Peiqi Platform")
                .contact(new Contact("JinMao", null, null))
                .version("1.0")
                .build();
    }
}
