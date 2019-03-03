package hw8.enums;

public enum Radiobuttons {

    FIRST(1), SECOND(2), THIRD(3), FOURTH(4), FIFTH(5), SIXTH(6), SEVENTH(7), EIGTH(8);

    public int serialNumber;

    Radiobuttons(int num) {

        this.serialNumber = num;
    }

    @Override
    public String toString() {

        return String.valueOf(serialNumber);
    }

    public static Radiobuttons getValueOf(int serialNumber) {
        for (Radiobuttons element : Radiobuttons.values()) {
            if (serialNumber == element.serialNumber) {
                return element;
            }
        }
        return null;
    }
}