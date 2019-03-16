package api2.core.constants;

public enum TestTexts {
    //todo вынеси в модуль test. Вообще, енам в этом случае не очень хорош и громоздок. Тестовыми данными будет достаточно тяжело манипулировать, добавлять новые
    MOTHER("mother", "mottherr"),
    BROTHER("brother", "bbrother"),
    EN_WORD("weather", "weatherrr"),
    RU_WORD("сметана", "смитана"),
    RU_WRONG_CAPITAL ("Санкт-Петербург", "санкт-петербург"),
    EN_WITH_DIGITS_BEGIN("122 father", "122father"),
    EN_WITH_DIGITS("father", "fa33ther"),
    EN_WITH_DIGITS_END("father 67", "father67"),
    RU_INCORRECT_SPACING("тест прошел","тестпрошел"),
    EN_INCORRECT_SPACING("test is", "testis good"),
    REPEAT_WORD_EN("so", "Weather was so so good"),
    REPEAT_WORD_RU("важны", "Тесты важны важны"),
    SEVERAL_ERRORS1("test", "tist is impotant"),
    SEVERAL_ERRORS("important", "tist is impotant");

    private String corrVer;
    private String wrongVer;

    public String corrVer(){return corrVer;}
    public String wrongVer(){return wrongVer;}

    private TestTexts (String corrVer, String wrongVer){
        this.corrVer = corrVer;
        this.wrongVer = wrongVer;
    }
}