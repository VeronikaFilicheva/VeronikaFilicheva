package jdi.enums;

public enum Vegetables {
    CUCUMBER("Cucumber"), TOMATO("Tomato"), VEGETABLES("Vegetables"), ONION("Onion");

    public String name;

    Vegetables(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
