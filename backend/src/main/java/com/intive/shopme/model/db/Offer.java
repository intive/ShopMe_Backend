package com.intive.shopme.model.db;

import com.intive.shopme.base.Identifiable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Offer extends Identifiable {

    private Date date;

    private String title;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Bundle bundle;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User user;

}
