package jdi.enums;

import java.util.ArrayList;
import java.util.List;

public enum MenuEnum {
    HOME("Home"),
    CONTACT_FORM("Contact form"),
    SERVICE("Service"),
    METALS_AND_COLORS("Metals & Colors"),
    SUPPORT("Support"),
    DATES("Dates"),
    COMPLEX_TABLE("Complex Table "),
    SIMPLE_TABLE("Simple Table "),
    TABLE_WITH_PAGES("Table with pages"),
    DIFFERENT_ELEMENTS("Different elements");

    public final String menuButton;

    MenuEnum(String page) {
        this.menuButton = page;
    }

}