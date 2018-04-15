package com.intive.shopme.base.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

@Data
public abstract class IdentifiableView {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @ApiModelProperty(value = "Represents unique id number", position = 1,
            example = "5d214c01-95c3-4ec4-8f68-51dfb80b191c")
    private UUID id;
}
