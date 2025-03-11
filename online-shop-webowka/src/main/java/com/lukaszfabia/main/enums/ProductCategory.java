package com.lukaszfabia.main.enums;

public enum ProductCategory {
    FOOD("Food & Beverages"),
    ELECTRONICS("Electronics & Gadgets"),
    CLOTHING("Clothing & Apparel"),
    TOYS("Toys & Games"),
    BOOKS("Books & Stationery"),
    FURNITURE("Furniture & Decor"),
    BEAUTY("Beauty & Personal Care"),
    SPORTS("Sports & Outdoors");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
