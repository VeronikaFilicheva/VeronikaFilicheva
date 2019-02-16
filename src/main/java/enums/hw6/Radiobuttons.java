package enums.hw6;

public enum Radiobuttons {

    GOLD("Gold"),
    SILVER("Silver"),
    BRONZE("Bronze"),
    SELEN("Selen");

    public String name;

    Radiobuttons(String name) {
        this.name = name;
    }

    public static Radiobuttons getRadiobuttonByName(String nameRadio) {
        for (Radiobuttons radiobutton : values()) {
            if (radiobutton.name.equals(nameRadio)) {
                return radiobutton;
            }
        }
        throw new IllegalArgumentException("Wrong name: " + nameRadio);
    }
}