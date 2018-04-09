package com.intive.shopme.util;

import com.intive.shopme.repository.CategoryRepository;
import com.intive.shopme.repository.OfferRepository;
import com.intive.shopme.util.validation.error.NotFoundException;

import java.util.UUID;

public final class RepositoryVerifier {

    public static void throwNotFoundExceptionIfEntityNotFound(UUID id, CategoryRepository repository, String message) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(message);
        }
    }

    public static void throwNotFoundExceptionIfEntityNotFound(UUID id, OfferRepository repository, String message) {
        if (!repository.existsById(id)) {
            throw new NotFoundException(message);
        }
    }
}
