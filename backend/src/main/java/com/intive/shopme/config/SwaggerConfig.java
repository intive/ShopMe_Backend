package com.intive.shopme.config;

import com.google.common.base.Predicates;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.servlet.ServletContext;
import java.util.Collections;

import static com.intive.shopme.config.SwaggerApiInfoConfigurer.createApiInfo;

@Configuration
@EnableSwagger2
class SwaggerConfig {

    private final ServletContext servletContext;

    @Value("${server.external.host}")
    private String serverHost;

    @Value("${server.external.url.context}")
    private String serverContext;

    public SwaggerConfig(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Bean
    Docket api() {
        return handleReverseProxyMapping(
                new Docket(DocumentationType.SWAGGER_2)
                        .useDefaultResponseMessages(false)
                        .select()
                        .apis(RequestHandlerSelectors.any())
                        .paths(Predicates.not(PathSelectors.regex("/error|/actuator.*")))
                        .build()
                        .apiInfo(createApiInfo())
                        .securitySchemes(Collections.singletonList(apiKey())));
    }

    private ApiKey apiKey() {
        return new ApiKey("Authorization", "Authorization", "header");
    }

    @Bean
    WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
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
