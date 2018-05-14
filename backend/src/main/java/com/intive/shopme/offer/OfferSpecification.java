package com.intive.shopme.offer;

import com.intive.shopme.model.db.DbOffer;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;

@AllArgsConstructor
class OfferSpecification implements Specification<DbOffer> {

    private final SearchCriteria criteria;

    @Override
    public Predicate toPredicate(Root<DbOffer> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        Predicate result = null;

        var key = criteria.getKey();
        var operation = criteria.getOperation();
        if (key.equals("date")) {
            Path<Date> datePath = root.get(key);
            Date dateValue = (Date)criteria.getValue();
            switch (operation) {
                case "≥":
                    result = builder.greaterThanOrEqualTo(datePath, dateValue);
                    break;
                case "≤":
                    result = builder.lessThanOrEqualTo(datePath, dateValue);
                    break;
            }
        } else {
            Path path = root.get(key);
            var value = criteria.getValue();
            switch (operation) {
                case "≥":
                    result = builder.greaterThanOrEqualTo(path, value.toString());
                    break;
                case "≤":
                    result = builder.lessThanOrEqualTo(path, value.toString());
                    break;
                case ":":
                    if (path.getJavaType() == String.class) {
                        result = builder.like(builder.lower(path),"%" + value + "%");
                    } else {
                        result = builder.equal(path, value);
                    }
                    break;
            }
        }

        return result;
    }

}
