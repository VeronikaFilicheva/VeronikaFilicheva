package api2.core.constants;

public class YandexSpellerConstants {
    //todo хорошо иметь файл с константами, это праивльно. https://speller.yandex.net лучше вообще вынести в файл test.properties
    //todo, а endpoint оставить тут. Так тестовые запуски станут энво-независимыми :)

    public static final String YANDEX_SPELLER = "https://speller.yandex.net";
    public static final String SPELLER_JSON = "/spellservice.json/";
    public static final String PARAM_TEXTS = "text";
    public static final String PARAM_OPTIONS = "options";
    public static final String PARAM_LANGUAGES = "languages";

}
