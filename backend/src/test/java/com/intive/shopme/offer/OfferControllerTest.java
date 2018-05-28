package com.intive.shopme.offer;

import com.intive.shopme.model.db.DbCategory;
import com.intive.shopme.model.db.DbOffer;
import com.intive.shopme.model.db.DbUser;
import com.intive.shopme.model.db.DbVoivodeship;
import com.intive.shopme.model.rest.OfferWrite;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class OfferControllerTest {

    private static final UUID ID = UUID.randomUUID();
    private static final Date DATE = new Date(1520031600000L);
    private static final String TITLE = "foo";
    private static final String CATEGORY = "others";
    private static final DbCategory DB_CATEGORY = new DbCategory(CATEGORY);
    private static final String BASE_DESCRIPTION = "foo";
    private static final Double BASE_PRICE = 12.34;
    private static final String EXTENDED_DESCRIPTION = "bar";
    private static final Double EXTENDED_PRICE = 23.45;
    private static final String EXTRA_DESCRIPTION = "baz";
    private static final Double EXTRA_PRICE = 34.56;
    private static final String NAME = "foo name";
    private static final String EMAIL = "foo@bar.baz";
    private static final String VOIVODESHIP = "fooland";
    private static final DbVoivodeship DB_VOIVODESHIP = new DbVoivodeship(VOIVODESHIP);
    private static final String CITY = "foo";
    private static final String ADDITIONAL_INFO = "foo bar baz";
    private static final DbUser DB_USER = new DbUser(NAME, "", EMAIL, "", "", "",
            null, DB_VOIVODESHIP,  true, null, ADDITIONAL_INFO, null);

    private final OfferController controller = new OfferController(null, null, null,
            null);

    @Test
    void convertToView_should_map_basic_values_successfully() {
        final var offer = new DbOffer();

        offer.setId(ID);
        offer.setDate(DATE);
        offer.setTitle(TITLE);
        offer.setCategory(new DbCategory(CATEGORY));
        offer.setBaseDescription(BASE_DESCRIPTION);
        offer.setBasePrice(BASE_PRICE);
        offer.setExtendedDescription(EXTENDED_DESCRIPTION);
        offer.setExtendedPrice(EXTENDED_PRICE);
        offer.setExtraDescription(EXTRA_DESCRIPTION);
        offer.setExtraPrice(EXTRA_PRICE);
        offer.setUser(DB_USER);
        offer.setVoivodeship(DB_VOIVODESHIP);
        offer.setCity(CITY);

        final var result = controller.convertToView(offer);
        assertThat(result)
                .hasFieldOrPropertyWithValue("id", ID)
                .hasFieldOrPropertyWithValue("date", DATE)
                .hasFieldOrPropertyWithValue("title", TITLE)
                .hasFieldOrPropertyWithValue("category", CATEGORY)
                .hasFieldOrPropertyWithValue("baseDescription", BASE_DESCRIPTION)
                .hasFieldOrPropertyWithValue("basePrice", BASE_PRICE)
                .hasFieldOrPropertyWithValue("extendedDescription", EXTENDED_DESCRIPTION)
                .hasFieldOrPropertyWithValue("extendedPrice", EXTENDED_PRICE)
                .hasFieldOrPropertyWithValue("extraDescription", EXTRA_DESCRIPTION)
                .hasFieldOrPropertyWithValue("extraPrice", EXTRA_PRICE)
                .hasFieldOrPropertyWithValue("name", NAME)
                .hasFieldOrPropertyWithValue("voivodeship", VOIVODESHIP)
                .hasFieldOrPropertyWithValue("city", CITY)
                .hasFieldOrPropertyWithValue("additionalInfo", ADDITIONAL_INFO);
    }

    @Test
    void convertToDbModel_should_map_basic_values_successfully() {
        final var offer = new OfferWrite();

        offer.setTitle(TITLE);
        offer.setCategory(CATEGORY);
        offer.setBaseDescription(BASE_DESCRIPTION);
        offer.setBasePrice(BASE_PRICE);
        offer.setExtendedDescription(EXTENDED_DESCRIPTION);
        offer.setExtendedPrice(EXTENDED_PRICE);
        offer.setExtraDescription(EXTRA_DESCRIPTION);
        offer.setExtraPrice(EXTRA_PRICE);
        offer.setVoivodeship(VOIVODESHIP);
        offer.setCity(CITY);

        final var result = controller.convertToDbModel(offer);
        assertThat(result)
                .hasFieldOrProperty("category").isNotNull()
                .hasFieldOrPropertyWithValue("category", DB_CATEGORY)
                .hasFieldOrPropertyWithValue("baseDescription", BASE_DESCRIPTION)
                .hasFieldOrPropertyWithValue("basePrice", BASE_PRICE)
                .hasFieldOrPropertyWithValue("extendedDescription", EXTENDED_DESCRIPTION)
                .hasFieldOrPropertyWithValue("extendedPrice", EXTENDED_PRICE)
                .hasFieldOrPropertyWithValue("extraDescription", EXTRA_DESCRIPTION)
                .hasFieldOrPropertyWithValue("extraPrice", EXTRA_PRICE)
                .hasFieldOrProperty("voivodeship").isNotNull()
                .hasFieldOrPropertyWithValue("voivodeship", DB_VOIVODESHIP)
                .hasFieldOrPropertyWithValue("city", CITY);
    }
}
