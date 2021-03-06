package enums;

import java.util.ArrayList;
import java.util.List;

public enum  NavBarElements {

    HOME("HOME"),
    CONTACT_FORM("CONTACT FORM"),
    SERVICE("SERVICE"),
    METALS_CLORS("METALS & COLORS");

    public String name;

    NavBarElements(String name) {
        this.name = name;
    }

    public static List<String> getNavBarCategoriesList() {
        List<String> result = new ArrayList<>();
        for (NavBarElements category : NavBarElements.values()) {
            result.add(category.name);
        }
        return result;
    }

}
