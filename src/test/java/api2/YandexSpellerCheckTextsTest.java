package api2;

import api2.beans.YandexSpellerAnswer;
import api2.core.constants.YandexSpellerConstants.*;
import api2.core.YandexSpellerApiCheckTexts;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;


import java.util.List;

import static api2.core.constants.YandexSpellerConstants.PARAM_LANGUAGES;
import static api2.core.constants.YandexSpellerConstants.YANDEX_SPELLER_API_URI;
import static api2.core.constants.ErrorCodes.*;
import static api2.core.constants.TestTexts.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

public class YandexSpellerCheckTextsTest {

    @Test
    public void checkUnknownWordError() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.EN)
                        .options(0)
                        .texts(MOTHER.wrongVer(), BROTHER.wrongVer())
                        .callApi());

        assertThat("Error is incorrect", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion EN 1", answer.get(0).s, hasItem(MOTHER.corrVer()));
        assertThat("Wrong suggestion EN 2", answer.get(1).s, hasItem(BROTHER.corrVer()));
    }

    @Test
    public void checkIncorrectSpacing() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with().
                        language(Languages.EN, Languages.RU)
                        .options(0)
                        .texts(EN_INCORRECT_SPACING.wrongVer(), RU_INCORRECT_SPACING.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion EN", answer.get(0).s, hasItem(EN_INCORRECT_SPACING.corrVer()));
        assertThat("Wrong suggestion RU", answer.get(1).s, hasItem(RU_INCORRECT_SPACING.corrVer()));
    }

    // bug, response should contain 400 Status Code
    @Test
    public void checkResponseCodeInCaseOfWrongRequest() {
        RestAssured
                .given()
                .param(PARAM_LANGUAGES, Languages.IT)
                .log().everything()
                .when()
                .get(YANDEX_SPELLER_API_URI)
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    //Service Bug
    @Test
    public void checkRepeatWordOption() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.RU)
                        .options(8)
                        .texts(REPEAT_WORD_RU.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_REPEAT_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(REPEAT_WORD_RU.corrVer()));
    }

    //Service Bug
    @Test
    public void checkIgnoreCapitalLettersAndDigitsOption() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.RU)
                        .options(2, 4, 512)
                        .texts(RU_WRONG_CAPITAL.wrongVer())
                        .callApi());
        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_CAPITALIZATION.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(REPEAT_WORD_RU.corrVer()));
    }

    @Test
    public void checkErrorAttributesInResponse() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.EN)
                        .options(0)
                        .texts(MOTHER.wrongVer(), BROTHER.wrongVer())
                        .callApi());

        assertThat(answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat(answer.get(0).pos, equalTo(MOTHER.wrongVer().indexOf("mottherr")));
        assertThat(answer.get(0).row, equalTo(0));
        assertThat(answer.get(0).col, equalTo(0));
        assertThat(answer.get(0).len, equalTo(answer.get(0).word.length()));
        assertThat(answer.get(0).s, hasItem(MOTHER.corrVer()));

        assertThat(answer.get(1).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat(answer.get(1).pos, equalTo(BROTHER.wrongVer().indexOf("bbrother")));
        assertThat(answer.get(1).row, equalTo(0));
        assertThat(answer.get(1).col, equalTo(0));
        assertThat(answer.get(1).len, equalTo(answer.get(0).word.length()));
        assertThat(answer.get(1).s, hasItem(BROTHER.corrVer()));

    }

    //Service Bug
    @Test
    public void checkOptionToIgnoreDigits() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.EN)
                        .options(2)
                        .texts(EN_WITH_DIGITS.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(EN_WITH_DIGITS.corrVer()));
    }

    @Test
    public void incorrectRuWordInEnDictionaryCheck() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.EN)
                        .options(2)
                        .texts(MOTHER.wrongVer(), RU_WORD.wrongVer(), EN_WORD.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(1).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(1).s, hasItem(RU_WORD.corrVer()));
    }

    @Test
    public void incorrectEnWordInRuDictionaryCheck() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.RU)
                        .options(2)
                        .texts(MOTHER.wrongVer(), RU_WORD.wrongVer(), EN_WORD.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(MOTHER.corrVer()));

        assertThat("Incorrect Error",answer.get(2).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(2).s, hasItem(EN_WORD.corrVer()));
    }

    @Test
    public void checkWordsWithDigits() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.EN)
                        .options(0)
                        .texts(EN_WITH_DIGITS.wrongVer(), EN_WITH_DIGITS_BEGIN.wrongVer(),EN_WITH_DIGITS_END.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(EN_WITH_DIGITS.corrVer()));

        assertThat("Incorrect Error", answer.get(1).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(1).s, hasItem(EN_WITH_DIGITS_BEGIN.corrVer()));

        assertThat("Incorrect Error", answer.get(2).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(2).s, hasItem(EN_WITH_DIGITS_END.corrVer()));
    }

    @Test
    public void checkDefaultLanguage() {
        List<YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .texts(RU_WORD.wrongVer(), EN_WORD.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(RU_WORD.corrVer()));

        assertThat("Incorrect Error", answer.get(1).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(1).s, hasItem(EN_WORD.corrVer()));
    }

}