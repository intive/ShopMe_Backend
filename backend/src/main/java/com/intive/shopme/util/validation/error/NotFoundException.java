package com.intive.shopme.util.validation.error;

import lombok.Data;

@Data
public class NotFoundException extends RuntimeException {

    private final String name;
}
