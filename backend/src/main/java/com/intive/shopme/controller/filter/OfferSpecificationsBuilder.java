package com.intive.shopme.controller.filter;

import com.intive.shopme.model.Offer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class OfferSpecificationsBuilder {

    private final List<SearchCriteria> params = new ArrayList<>();

    public OfferSpecificationsBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Offer> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<Specification<Offer>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new OfferSpecification(param));
        }

        Specification<Offer> result = specs.get(0);
        for (int i = 1; i < specs.size(); i++) {
            result = Specification.where(result).and(specs.get(i));
        }

        return result;
    }

}
