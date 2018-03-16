package com.intive.shopme.controller.filter;

import lombok.Data;
import lombok.AllArgsConstructor;

@Data
@AllArgsConstructor
public class SearchCriteria {
    private String key;
    private String operation;
    private Object value;
}
