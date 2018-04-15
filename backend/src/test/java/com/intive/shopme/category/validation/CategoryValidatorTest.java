package com.intive.shopme.category.validation;

import com.intive.shopme.category.model.db.Category;
import com.intive.shopme.offer.model.view.OfferView;
import com.intive.shopme.category.CategoryService;
import junit5.workaround.MockitoExtension;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CategoryValidatorTest {
/*
    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ServiceUtil serviceUtil;

    private static Validator validator;

    private final static UUID ID = UUID.randomUUID();
    private final static Category CATEGORY = createCategory(ID);
    private final static OfferView OFFER = createOffer(CATEGORY);

    @BeforeEach
    void setUp() {
        this.serviceUtil.fillInstance();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    void testCategoryValidatorAccept() {
        when(categoryService.getCategoryById(ID)).thenReturn(CATEGORY);

        Set<ConstraintViolation<OfferView>> constraintValidations = validator.validateProperty(OFFER, "category");
        assertThat(constraintValidations).isEmpty();
    }

    @Test
    void testCategoryValidatorReject() {
        when(categoryService.getCategoryById(any(UUID.class))).thenReturn(null);

        Set<ConstraintViolation<OfferView>> constraintValidations = validator.validateProperty(OFFER, "category");
        assertThat(constraintValidations).hasSize(1);
    }

    private static OfferView createOffer(Category category) {
        final OfferView sampleOffer = new OfferView();
        sampleOffer.setCategory(category);
        return sampleOffer;
    }

    private static Category createCategory(UUID id) {
        final Category category = new Category();
        category.setId(id);
        category.setName("foo");
        return category;
    }*/
}
