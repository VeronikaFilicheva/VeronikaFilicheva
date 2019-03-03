package hw8.enums;

public enum Result {

    SUMMARY("Summary"), ELEMENTS("Elements"), COLOR("Color"), METAL("Metal"), VEGETABLES("Vegetables");

    public String name;

    Result(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}