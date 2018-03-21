package com.intive.shopme.util.validation.validation;

import com.intive.shopme.model.Category;
import com.intive.shopme.model.Offer;
import com.intive.shopme.service.CategoryService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class CategoryValidatorTest {

    private static Validator validator;

    @Mock
    CategoryService categoryService;

    @InjectMocks
    ServiceUtil serviceUtil;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.serviceUtil.fillInstance();
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testCategoryValidatorAccept() {
        Category category = new Category();
        UUID uuid1 = UUID.randomUUID();
        category.setId(uuid1);
        category.setName("budowa");

        Offer testOffer = new Offer();
        testOffer.setCategory(category);

        when(categoryService.getCategoryById(uuid1)).thenReturn(category);

        Set<ConstraintViolation<Offer>> constraintValidations = validator.validateProperty(testOffer, "category");
        assertEquals(0, constraintValidations.size());
    }

    @Test
    public void testCategoryValidatorReject() {
        Category category2 = new Category();
        UUID uuid2 = UUID.randomUUID();
        category2.setId(uuid2);
        category2.setName("wrong category");

        Offer testOffer = new Offer();
        testOffer.setCategory(category2);

        when(categoryService.getCategoryById(uuid2)).thenReturn(null);

        Set<ConstraintViolation<Offer>> constraintValidations = validator.validateProperty(testOffer, "category");
        assertEquals(1, constraintValidations.size());
    }
}
