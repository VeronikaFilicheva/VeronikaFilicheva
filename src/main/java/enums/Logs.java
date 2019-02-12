package enums;

public enum Logs {


    VALUE_CHANGED_TO {
        @Override
        public String toString() {
            return ": value changed to ";
        }
    },

    METAL {
        @Override
        public String toString() {
            return "metal";
        }
    },

    COLORS {
        @Override
        public String toString() {
            return "Colors";
        }
    },
}