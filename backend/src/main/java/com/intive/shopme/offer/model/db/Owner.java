package com.intive.shopme.offer.model.db;

import com.intive.shopme.base.model.Identifiable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import static com.intive.shopme.configuration.api.AppConfiguration.USER_DESCRIPTION_MAX_LENGTH;
import static com.intive.shopme.configuration.api.AppConfiguration.USER_NAME_MAX_LENGTH;
import static com.intive.shopme.configuration.api.AppConfiguration.USER_NAME_MIN_LENGTH;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class Owner extends Identifiable {

    private String name;

    private String email;

    private String phoneNumber;

    private String additionalInfo;

}
