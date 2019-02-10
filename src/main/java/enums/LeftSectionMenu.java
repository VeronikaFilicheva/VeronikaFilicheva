package enums;

import java.util.ArrayList;
import java.util.List;

public enum LeftSectionMenu {

    SERVICE_SUPPORT("Support"),
    SERVICE_DATES("Dates"),
    SERVICE_COMPLEX_TABLE("Complex Table"),
    SERVICE_SIMPLE_TABLE("Simple Table"),
    SERVICE_USER_TABLE("User Table"),
    SERVICE_TABLE_WITH_PAGES("Table with pages"),
    SERVICE_DIFFERENT_ELEMENTS("Different elements"),
    SERVICE_PERFORMANCE("Performance");

    String name;

    LeftSectionMenu(String name) {
        this.name = name;
    }

    public static List<String> getLeftSectionCategoriesList() {
        List<String> result = new ArrayList<String>();
        for (LeftSectionMenu category : LeftSectionMenu.values()) {
            result.add(category.name);
        }
        return result;
    }
}