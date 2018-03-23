package com.intive.shopme.configuration;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.intive.shopme.configuration.SwaggerApiConstants.apiInfo;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(apiInfo());
    }
}

final class SwaggerApiConstants {

    private static final String TITLE = "ShopMe by intive Patronage `18 team";
    private static final String DESC = "ShopMe is a Web Application created during intive Patronage `18 Project";
    private static final String VERSION = "1.1";

    private SwaggerApiConstants() {
    }

    static ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(TITLE).description(DESC).version(VERSION).build();
    }
}
