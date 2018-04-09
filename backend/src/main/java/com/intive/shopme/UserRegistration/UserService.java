package com.intive.shopme.UserRegistration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

@Service
@Transactional
class UserService {

    private static final ValidatorFactory VALIDATOR_FACTORY = Validation.buildDefaultValidatorFactory();

    private final UserRepository repository;

    @Autowired
    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public Users add(Users user) {
        validate(user);
        if (!user.getInvoiceRequest()) {
            user.setInvoice(null);
        }
        return repository.save(user);
    }

    public Users get(UUID id) {
        return repository.findById(id).orElse(null);
    }

    private void validate(Users user) {
        final Validator validator = VALIDATOR_FACTORY.getValidator();
        Set<ConstraintViolation<Users>> violations = validator.validate(user);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

}
