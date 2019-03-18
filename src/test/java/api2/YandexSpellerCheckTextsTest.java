package api2;

import api2.beans.YandexSpellerAnswer;
import api2.core.CheckResponse;
import api2.core.YandexSpellerApi;
import api2.core.constants.Languages;
import api2.data.DataProviders;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;
import static api2.core.constants.ErrorCodes.*;
import static api2.core.CheckResponse.*;
import static api2.core.constants.SoapActions.*;
import static api2.core.constants.YandexSpellerConstants.*;


public class YandexSpellerCheckTextsTest {
    CheckResponse checkingAnswer = new CheckResponse();

    @Test(dataProvider = "unknownWordError", dataProviderClass = DataProviders.class)
    public void checkUnknownWordError(String[] texts, Languages lang, List[] expectedSuggestions) {

        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .options(0)
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);

    }

    @Test(dataProvider = "incorrectSpacing", dataProviderClass = DataProviders.class)
    public void checkIncorrectSpacing(String[] texts, Languages lang, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .options(0)
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);

    }

    // bug, response should contain 400 Status Code
    @Test
    public void checkResponseCodeInCaseOfWrongRequest() {
        RestAssured
                .given()
                .param(PARAM_LANGUAGES, Languages.IT)
                .log().everything()
                .when()
                .get(YANDEX_SPELLER+SPELLER_JSON +CHECK_TEXTS.getMethod())
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);

    }

    //Service Bug
    @Test(dataProvider = "repeatWords", dataProviderClass = DataProviders.class)
    public void checkRepeatWordOption(String[] texts, Languages lang, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .options(8)
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_REPEAT_WORD);
    }

    ///Service Bug
    @Test(dataProvider = "digitAndCapitalization", dataProviderClass = DataProviders.class)
    public void checkIgnoreCapitalLettersAndDigitsOption(String[] texts, Languages lang, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .options(2,4,512)
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_CAPITALIZATION);
    }

    //Service Bug
    @Test(dataProvider = "digitAndCapitalization", dataProviderClass = DataProviders.class)
    public void checkOptionToIgnoreDigits(String[] texts, Languages lang, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .options(2)
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);
    }

    @Test(dataProvider = "wordsWithDigits", dataProviderClass = DataProviders.class)
    public void checkWordsWithDigits(String[] texts, Languages lang, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .options(0)
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);
    }

    @Test(dataProvider = "incorrectRuWord", dataProviderClass = DataProviders.class)
    public void incorrectRuWordInEnDictionaryCheck(String[] texts, Languages lang, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .options(0)
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);
    }

    @Test(dataProvider = "ruEnTexts", dataProviderClass = DataProviders.class)
    public void checkDefaultLanguage(String[] texts, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);
    }

    @Test(dataProvider = "severalErrors", dataProviderClass = DataProviders.class)
    public void checkSeveralErrorsInText(String[] texts, List[] expectedSuggestions) {
        List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .texts(texts)
                        .getCheckTexts());

        checkAnswer(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);
    }

    @Test(dataProvider = "unknownWordError", dataProviderClass = DataProviders.class)
    public void checkErrorAttributesInResponse(String[] texts, Languages lang, List[] expectedSuggestions) {
         List<List<YandexSpellerAnswer>> answer =
                YandexSpellerApi.getYandexSpellerAnswers(YandexSpellerApi.with()
                        .language(lang)
                        .texts(texts)
                        .getCheckTexts());

         checkErrorAttribute(texts, expectedSuggestions, answer, ERROR_UNKNOWN_WORD);

    }

}
