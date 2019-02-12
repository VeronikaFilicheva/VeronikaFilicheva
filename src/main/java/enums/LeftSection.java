package enums;

import java.util.ArrayList;
import java.util.List;

public enum LeftSection {

    HOME("HOME"),
    CONTACT_FORM("CONTACT FORM"),
    SERVICE("SERVICE"),
    METALS_CLORS("METALS & COLORS");

    public String name;

    LeftSection(String name) {
        this.name = name;
    }

    public static List<String> getLeftSectionMenuList() {
        List<String> result = new ArrayList<>();
        for (LeftSection category : LeftSection.values()) {
            result.add(category.name);
        }
        return result;
    }

}
