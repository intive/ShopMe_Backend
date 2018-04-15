package com.intive.shopme.model.rest;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.UUID;

@Data
public abstract class Identifiable {

    @ApiModelProperty(value = "Represents unique id number", position = 1,
            example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;
}
