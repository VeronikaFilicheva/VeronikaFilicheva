package enums.hw6;

public enum Checkboxes {

    WATER("Water"),
    EARTH("Earth"),
    WIND("Wind"),
    FIRE("Fire");

    public String checkboxesName;

    Checkboxes(String checkboxesName) {
        this.checkboxesName = checkboxesName;
    }

    public static Checkboxes getCheckboxByName(String name) {
        for (Checkboxes checkbox : values()) {
            if (checkbox.checkboxesName.equals(name)) {
                return checkbox;
            }
        }
        throw new IllegalArgumentException("Wrong name: " + name);
    }
}