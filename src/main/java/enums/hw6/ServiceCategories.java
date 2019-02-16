package enums.hw6;

import java.util.ArrayList;
import java.util.List;

public enum ServiceCategories {
    HEADER_SUPPORT("SUPPORT"),
    HEADER_DATES("DATES"),
    HEADER_COMPLEX_TABLE("COMPLEX TABLE"),
    HEADER_SIMPLE_TABLE("SIMPLE TABLE"),
    HEADER_USER_TABLE("USER TABLE"),
    HEADER_TABLE_WITH_PAGES("TABLE WITH PAGES"),
    HEADER_DIFFERENT_ELEMENTS("DIFFERENT ELEMENTS"),
    HEADER_PERFORMANCE("PERFORMANCE");

    public String name;


    ServiceCategories(String name) {
        this.name = name;
    }

    public static List<String> getHeaderCategoriesList() {
        List<String> result = new ArrayList<String>();
        for (ServiceCategories category : ServiceCategories.values()) {
            result.add(category.name);
        }
        return result;
    }
    public static ServiceCategories getCategoryByName(String categoryName) {
        for (ServiceCategories category : values()) {
            if (category.name.equals(categoryName)) {
                return category;
            }
        }
        throw new IllegalArgumentException("Wrong user name: " + categoryName);
    }
}
