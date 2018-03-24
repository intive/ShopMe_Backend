package com.intive.shopme.util.validation.validation;

import com.intive.shopme.model.Category;
import com.intive.shopme.model.Offer;
import com.intive.shopme.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CategoryValidatorTest {

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ServiceUtil serviceUtil;

    private static Validator validator;

    private final static UUID ID = UUID.randomUUID();
    private final static Category CATEGORY = createCategory(ID);
    private final static Offer OFFER = createOffer(CATEGORY);

    @Before
    public void setUp() {
        this.serviceUtil.fillInstance();
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testCategoryValidatorAccept() {
        when(categoryService.getCategoryById(ID)).thenReturn(CATEGORY);

        Set<ConstraintViolation<Offer>> constraintValidations = validator.validateProperty(OFFER, "category");
        assertThat(constraintValidations).isEmpty();
    }

    @Test
    public void testCategoryValidatorReject() {
        when(categoryService.getCategoryById(anyObject())).thenReturn(null);

        Set<ConstraintViolation<Offer>> constraintValidations = validator.validateProperty(OFFER, "category");
        assertThat(constraintValidations).hasSize(1);
    }

    private static Offer createOffer(Category category) {
        final Offer sampleOffer = new Offer();
        sampleOffer.setCategory(category);
        return sampleOffer;
    }

    private static Category createCategory(UUID id) {
        final Category category = new Category();
        category.setId(id);
        category.setName("foo");
        return category;
    }
}
