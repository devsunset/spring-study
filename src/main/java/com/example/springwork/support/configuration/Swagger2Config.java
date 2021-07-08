package com.example.springwork.support.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springwork.controller"))
                // Api Annotation 으로 선언된 부분만 swagger 로 노출되도록 설정
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                  //.paths(PathSelectors.ant("api/v1/**"))
                .build()
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(false)
                .apiInfo(new ApiInfoBuilder()
                .title("spring-work") 
                .version("v1") 
                .description("spring-work swagger example")
                .license("Apache License 2.0") 
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0") 
                                .build()
                );
    }
    
}
