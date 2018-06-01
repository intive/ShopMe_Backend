package com.intive.shopme.offer;

import com.intive.shopme.model.db.DbOffer;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

class OfferSpecificationsBuilder {

    private final List<SearchCriteria> params = new ArrayList<>();

    void with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
    }

    void empty() {
        params.add(new SearchCriteria("empty", null, null));
    }

    Specification<DbOffer> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<Specification<DbOffer>> specs = new ArrayList<>();
        for (SearchCriteria param : params) {
            specs.add(new OfferSpecification(param));
        }

        Specification<DbOffer> result = specs.get(0);
        for (Specification<DbOffer> spec : specs) {
            result = Specification.where(result).and(spec);
        }

        return result;
    }
}
