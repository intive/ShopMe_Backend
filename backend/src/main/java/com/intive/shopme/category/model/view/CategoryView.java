package com.intive.shopme.category.model.view;

import com.intive.shopme.base.model.IdentifiableView;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;

@ApiModel(value = "OfferView's category", description = "Represents different category types of an offer")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class CategoryView extends IdentifiableView {

    @ApiModelProperty(value = "Represents category's name", required = true, position = 2, example = "inne")
    @NotEmpty
    private String name;

    @ApiModelProperty(value = "Represents key for translate operation", required = true, position = 3, example = "others")
    @NotEmpty
    private String translateKey;
}
