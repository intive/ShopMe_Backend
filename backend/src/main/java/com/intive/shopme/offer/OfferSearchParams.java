package com.intive.shopme.offer;

import com.intive.shopme.model.db.DbCategory;
import com.intive.shopme.model.db.DbOffer;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.Date;

import static com.intive.shopme.config.AppConfig.ACCEPTABLE_TITLE_SEARCH_CHARS;
import static com.intive.shopme.config.AppConfig.DEFAULT_PAGE_SIZE;
import static com.intive.shopme.config.AppConfig.DEFAULT_SORT_DIRECTION;
import static com.intive.shopme.config.AppConfig.DEFAULT_SORT_FIELD;
import static com.intive.shopme.config.AppConfig.FIRST_PAGE;
import static com.intive.shopme.config.AppConfig.OFFER_TITLE_MAX_LENGTH;
import static com.intive.shopme.config.AppConfig.PAGE_SIZE_MAX;

@Data
class OfferSearchParams {

    @ApiParam(value = "requested page number (optional, counting from " + FIRST_PAGE + ", default " + FIRST_PAGE + ")",
            allowableValues = "range[0, infinity]",
            defaultValue = "" + FIRST_PAGE)
    @ApiModelProperty(position = 1)
    @Min(value = FIRST_PAGE, message = "Requested page number cannot be lower than " + FIRST_PAGE + ".")
    private int page = FIRST_PAGE;

    @ApiParam(value = "number of offers per page (optional, default " + DEFAULT_PAGE_SIZE + ", max " + PAGE_SIZE_MAX + ")",
            allowableValues = "range[1, " + PAGE_SIZE_MAX + "]",
            defaultValue = "" + DEFAULT_PAGE_SIZE)
    @ApiModelProperty(position = 2)
    @Min(value = 1, message = "Requested page size cannot be lower than 1.")
    @Max(value = 100, message = "Requested page size cannot be greater than " + PAGE_SIZE_MAX + ".")
    private int pageSize = DEFAULT_PAGE_SIZE;

    @ApiModelProperty(value = "filter query for offers title (optional, at least two characters)", position = 3)
    @Size(max = OFFER_TITLE_MAX_LENGTH,
            message = "Title search query has too many characters (max " + OFFER_TITLE_MAX_LENGTH + ").")
    private String title;

    @ApiModelProperty(position = 4)
    @ApiParam(value = "category which offer belongs to (optional, acceptable values: bandsAndMusic | bookkeeping | " +
            "building | companyAndOffice | garden | graphics | housework | law | marketingAndAdvertising | others | " +
            "photography | programming | repairAndService | translations | transport | tutoring | workshopServices)",
            allowableValues = "bandsAndMusic, bookkeeping, building, companyAndOffice, garden, graphics, housework, " +
                    "law, marketingAndAdvertising, others, photography, programming, repairAndService, translations, " +
                    "transport, tutoring, workshopServices")
    private String category;

    @ApiParam(value = "the property to sort by (optional, acceptable values: date | basePrice | title, default " +
            DEFAULT_SORT_FIELD + ")",
            allowableValues = "date, basePrice, title",
            defaultValue = DEFAULT_SORT_FIELD)
    @ApiModelProperty(position = 5)
    @Pattern(regexp = "date|basePrice|title", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Acceptable sort properties are: date, basePrice and title")
    private String sort = DEFAULT_SORT_FIELD;

    @ApiParam(value = "sorting order (optional, acceptable values: ASC | DESC, default " + DEFAULT_SORT_DIRECTION + ")",
            allowableValues = "ASC, DESC",
            defaultValue = DEFAULT_SORT_DIRECTION)
    @ApiModelProperty(position = 6)
    @Pattern(regexp = "ASC|DESC", flags = Pattern.Flag.CASE_INSENSITIVE,
            message = "Acceptable sort order values are ASC and DESC")
    private String order = DEFAULT_SORT_DIRECTION;

    @ApiModelProperty(value = "minimum price (optional)", position = 7, allowableValues = "range[0, infinity]")
    @PositiveOrZero(message = "Offer's minimum price cannot be negative")
    private float priceMin;

    @ApiModelProperty(value = "maximum price (optional)", position = 8, allowableValues = "range[0, infinity]")
    @PositiveOrZero(message = "Offer's maximum price cannot be negative")
    private float priceMax;

    @ApiModelProperty(value = "offer not older than (optional, EPOCH time in milliseconds)", position = 9)
    private long dateMin;

    @ApiModelProperty(value = "offer not newer than (optional, EPOCH time in milliseconds)", position = 10)
    private long dateMax;

    Pageable pageable() {
        return PageRequest.of(page - FIRST_PAGE, pageSize, Sort.Direction.fromString(order), sort);
    }

    Specification<DbOffer> filter() {
        final var builder = new OfferSpecificationsBuilder();

        var titleKeywordsIsInvalid = false;
        if (StringUtils.isNotEmpty(title)) {
            titleKeywordsIsInvalid = true;
            if ((title.length() > 1) && !StringUtils.isNumeric(title)){
                var titleKeywords = StringUtils.left(title, OFFER_TITLE_MAX_LENGTH)
                        .replaceAll("[^" + ACCEPTABLE_TITLE_SEARCH_CHARS + "]", "")
                        .replaceAll("  ", " ")
                        .toLowerCase().split(" ");
                for (String titleKeyword : titleKeywords) {
                    if (StringUtils.isNotEmpty(titleKeyword)) {
                        titleKeywordsIsInvalid = false;
                        builder.with("title", ":", titleKeyword);
                    }
                }
            }
        }
        if (titleKeywordsIsInvalid) {
            builder.empty();
        }

        if (dateMin != 0) {
            builder.with("date", "≥", new Date(dateMin));
        }
        if (dateMax != 0) {
            builder.with("date", "≤", new Date(dateMax));
        }

        if (StringUtils.isNotEmpty(category)) {
            builder.with("category", ":", new DbCategory(category));
        }

        if (priceMin != 0) {
            builder.with("basePrice", "≥", priceMin);
        }
        if (priceMax != 0) {
            builder.with("basePrice", "≤", priceMax);
        }

        return builder.build();
    }
}
