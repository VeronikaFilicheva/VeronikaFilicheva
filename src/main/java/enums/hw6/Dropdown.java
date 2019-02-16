package enums.hw6;

public enum Dropdown {

    RED("Red"),
    GREEN("Green"),
    BLUE("Blue"),
    YELLOW("Yellow");

    public String color;

    Dropdown(String color) {
        this.color = color;
    }

    public static Dropdown getDropdownByName(String nameColor) {
        for (Dropdown dropdown : values()) {
            if (dropdown.color.equals(nameColor)) {
                return dropdown;
            }
        }
        throw new IllegalArgumentException("Wrong name: " + nameColor);
    }
}
