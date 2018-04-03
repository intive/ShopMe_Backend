package com.intive.shopme.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;

final class SwaggerApiInfoConfigurer {

    private static final String TITLE = "ShopMe by Intive Patronage `18 team";
    private static final String DESC = "ShopMe is a Web Application created during Intive Patronage `18 Project";
    private static final String VERSION = "1.2";

    private SwaggerApiInfoConfigurer() {
    }

    static ApiInfo createApiInfo() {
        return new ApiInfoBuilder().title(TITLE).description(DESC).version(VERSION).build();
    }

}
