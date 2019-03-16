package api2.core.constants;

public class YandexSpellerConstants {
    //todo хорошо иметь файл с константами, это праивльно. https://speller.yandex.net лучше вообще вынести в файл test.properties
    //todo, а endpoint оставить тут. Так тестовые запуски станут энво-независимыми :)

    public static final String YANDEX_SPELLER_API_URI = "https://speller.yandex.net/services/spellservice.json/";
    public static final String PARAM_TEXTS = "text";
    public static final String PARAM_OPTIONS = "options";
    public static final String PARAM_LANGUAGES = "languages";

    public enum Languages {
        EN("en"),
        RU("ru"),
        UK("uk"),
        IT("it");

        private final String lang;

        Languages(String lang) {
            this.lang = lang;
        }

        public String getLang() {
            return lang;
        }
    }
}
