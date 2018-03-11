package com.intive.shopme.model.db;

import com.intive.shopme.base.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bundle extends Identifiable {

    private String description;

    private String price;

}
