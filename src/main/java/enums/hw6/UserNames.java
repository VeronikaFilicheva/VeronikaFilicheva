package enums.hw6;

public enum UserNames {

        SERGEY_IVAN("Sergey Ivan", "ivan"),
        ROMAN("Roman", "roman");

        public String name;
        public String id;

        UserNames(String name, String id) {
            this.name = name;
            this.id = id;
        }

        public static String getUserIdByName(String name) {
            for (UserNames user : UserNames.values()) {
                if (user.name.equals(name)) {
                    return user.id;
                }
            }
            return null;
        }

}