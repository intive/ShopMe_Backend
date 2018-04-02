package com.intive.shopme.controller.filter;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
class SearchCriteria {

    private final String key;
    private final String operation;
    private final Object value;

}
