package com.intive.shopme.util.validation.validation;

public enum CategoryEnum {

    cat1("budowa"),
    cat2("ogród"),
    cat3("usługi warsztatowe"),
    cat4("transport"),
    cat6("fotografia"),
    cat7("grafika"),
    cat8("marketing i reklama"),
    cat9("programowanie"),
    cat10("księgowość"),
    cat11("prawo"),
    cat12("tłumaczenia"),
    cat13("firma i biuro"),
    cat14("naprawa i serwis"),
    cat15("prace domowe"),
    cat16("korepetycje"),
    cat17("zespoły i muzyka"),
    cat18("inne");

    private final String category;

    CategoryEnum(String category) {
        this.category = category;
    }

    public boolean equalsCategory(String otherCategory) {
        return category.equals(otherCategory);
    }

    public String toString() {
        return this.category;
    }
}