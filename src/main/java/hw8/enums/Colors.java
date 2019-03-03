package hw8.enums;

public enum Colors {

    COLORS("Colors"), RED("Red"), GREEN("Green"), BLUE("Blue"), YELLOW("Yellow");

    public String name;

    Colors(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
