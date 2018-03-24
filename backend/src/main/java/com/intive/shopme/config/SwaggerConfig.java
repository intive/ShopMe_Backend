package com.intive.shopme.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;

import static com.intive.shopme.config.SwaggerApiInfoConfigurer.createApiInfo;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private final ServletContext servletContext;

    @Value("${server.external.host}")
    private String serverHost;

    @Value("${server.external.url.context}")
    private String serverContext;

    @Autowired
    public SwaggerConfig(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Bean
    Docket api() {
        return handleReverseProxyMapping(
                new Docket(DocumentationType.SWAGGER_2)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .paths(Predicates.not(PathSelectors.regex("/error")))
                        .build()
                        .apiInfo(createApiInfo()));
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE");
            }
        };
    }

    private Docket handleReverseProxyMapping(Docket docket) {
        return docket
                .host(serverHost)
                .pathProvider(createPathProvider());
    }

    private RelativePathProvider createPathProvider() {
        return new RelativePathProvider(this.servletContext) {
            @Override
            public String getApplicationBasePath() {
                return serverContext;
            }
        };
    }
}

final class SwaggerApiInfoConfigurer {

    private static final String TITLE = "ShopMe by Intive Patronage `18 team";
    private static final String DESC = "ShopMe is a Web Application created during Intive Patronage `18 Project";
    private static final String VERSION = "1.1";

    private SwaggerApiInfoConfigurer() {
    }

    static ApiInfo createApiInfo() {
        return new ApiInfoBuilder().title(TITLE).description(DESC).version(VERSION).build();
    }
}

