package com.intive.shopme.voivodeship;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class VoivodeshipValidator implements Validator {

    private final VoivodeshipService voivodeshipService;

    public VoivodeshipValidator(VoivodeshipService voivodeshipService) {
        this.voivodeshipService = voivodeshipService;
    }

    @Override
    public boolean supports(Class<?> type) {
        return type == String.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        var voivodeshipName = (String) target;
        if (!voivodeshipService.exists(voivodeshipName)) {
            errors.rejectValue("voivodeship", "","Voivodeship name is not known: " + voivodeshipName);
        }
    }
}
