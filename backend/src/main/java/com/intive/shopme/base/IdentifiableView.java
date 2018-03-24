package com.intive.shopme.base;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class IdentifiableView {

    @ApiModelProperty(example = "c5296892-347f-4b2e-b1c6-6faff971f767", readOnly = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;
}
