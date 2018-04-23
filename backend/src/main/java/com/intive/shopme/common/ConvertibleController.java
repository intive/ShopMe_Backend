package com.intive.shopme.common;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 * Common system ability to convert from/to db/rest model.
 *
 * @param <DB> DB model class
 * @param <V>  View/REST model class
 */
public abstract class ConvertibleController<DB, V> {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private final Class<DB> dbClass;
    private final Class<V> vClass;

    public ConvertibleController(Class<DB> dbClass, Class<V> vClass) {
        this.vClass = vClass;
        this.dbClass = dbClass;
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static <D> D genericConvert(Object source, Class<D> aClass) {
        return OBJECT_MAPPER.convertValue(source, aClass);
    }

    protected V convertToView(final DB dbObject) {
        return genericConvert(dbObject, this.vClass);
    }

    protected List<V> convertToView(final Collection<DB> dbCollection) {
        return dbCollection.stream()
                .map(this::convertToView)
                .collect(toList());
    }

    protected DB convertToDbModel(final V viewObject) {
        return genericConvert(viewObject, dbClass);
    }

    protected List<DB> convertToDbModel(final Collection<V> viewCollection) {
        return viewCollection.stream()
                .map(this::convertToDbModel)
                .collect(toList());
    }
}
