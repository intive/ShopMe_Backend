package com.intive.shopme.category;

import com.intive.shopme.WebTierTest;
import com.intive.shopme.config.ApiUrl;
import com.intive.shopme.model.db.DbCategory;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import java.util.List;
import java.util.UUID;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO Beware: spring bean validation is not working -> happy path testing only is possible
@WebMvcTest(controllers = CategoryController.class)
class CategoryControllerTest extends WebTierTest {

    @MockBean
    private CategoryService service;

    @Test
    void get_should_successfully_return_non_empty_response() throws Exception {
        when(service.getAll()).thenReturn(buildSampleCategories());

        mockMvc.perform(get(ApiUrl.CATEGORIES))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(greaterThan(0))))
                .andExpect(jsonPath("$[0].name", notNullValue()))
                .andExpect(jsonPath("$[0].id", notNullValue()));
    }

    @Test
    void post_should_validate_successfully_valid_category() throws Exception {
        when(service.create(any(DbCategory.class))).thenReturn(buildSampleCategory());

        mockMvc.perform(
                post(ApiUrl.CATEGORIES)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(convertToJsonString(buildSampleCategory())))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name", notNullValue()))
                .andExpect(jsonPath("$.id", notNullValue()));
    }

    private static List<DbCategory> buildSampleCategories() {
        return List.of(buildSampleCategory());
    }

    private static DbCategory buildSampleCategory() {
        var result = new DbCategory();
        result.setName("foo");
        result.setId(UUID.randomUUID());
        return result;
    }
}
