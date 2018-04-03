package com.intive.shopme.util.validation.error;

import lombok.Data;
import lombok.Getter;

@Getter
@Data
public class NotFoundException extends RuntimeException {
    private final String name;

}
