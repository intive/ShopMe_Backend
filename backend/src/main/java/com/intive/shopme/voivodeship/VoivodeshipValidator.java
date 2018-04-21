package com.intive.shopme.voivodeship;

import com.intive.shopme.model.rest.User;
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
        return type == User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (target != null) {
            var voivodeship = ((User) target).getVoivodeship();
            if (voivodeship != null) {
                var id = voivodeship.getId();
                if (id != null && !voivodeshipService.getVoivodeshipById(id)) {
                    errors.rejectValue("voivodeship.id", "id.invalid", "Voivodeship id is not known: " + id);
                }
            }
        }
    }
}
