package api2;

import static api2.core.constants.ErrorCodes.ERROR_CAPITALIZATION;
import static api2.core.constants.ErrorCodes.ERROR_REPEAT_WORD;
import static api2.core.constants.ErrorCodes.ERROR_UNKNOWN_WORD;
import static api2.core.constants.TestTexts.*;
import static api2.core.constants.YandexSpellerConstants.PARAM_LANGUAGES;
import static api2.core.constants.YandexSpellerConstants.YANDEX_SPELLER_API_URI;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.MatcherAssert.assertThat;

import api2.core.YandexSpellerApiCheckTexts;
import api2.core.constants.YandexSpellerConstants.Languages;
import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.util.List;

public class YandexSpellerCheckTextsTest {

    @Test
    public void checkUnknownWordError() {
        List<beans.YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.EN)
                        .options(0)
                        .texts(MOTHER.wrongVer(), BROTHER.wrongVer())
                        .callApi());

//todo  assertThat("Error is incorrect", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
//todo  этот вызов повторяется ис следующих тестах. может быть вынесен в отделый класс с асертами
        //т.е. можно свестик методу AssertUnknownErrorCode(answer.get(0).code);
        assertThat("Error is incorrect", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion EN 1", answer.get(0).s, hasItem(MOTHER.corrVer()));
        assertThat("Wrong suggestion EN 2", answer.get(1).s, hasItem(BROTHER.corrVer()));
    }

    @Test
    public void checkIncorrectSpacing() {
        List<beans.YandexSpellerAnswer> answer =
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
        List<beans.YandexSpellerAnswer> answer =
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
        List<beans.YandexSpellerAnswer> answer =
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
        List<beans.YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .language(Languages.EN)
                        .options(0)
                        .texts(MOTHER.wrongVer(), BROTHER.wrongVer())
                        .callApi());

//todo делай проверку через expected объект, а не каждое поле поотдельности, а лучше сразу для листа. чтобы асерт был один, а не 12
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
        List<beans.YandexSpellerAnswer> answer =
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
        List<beans.YandexSpellerAnswer> answer =
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
        List<beans.YandexSpellerAnswer> answer =
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
        List<beans.YandexSpellerAnswer> answer =
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
        List<beans.YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .texts(RU_WORD.wrongVer(), EN_WORD.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(RU_WORD.corrVer()));

        assertThat("Incorrect Error", answer.get(1).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(1).s, hasItem(EN_WORD.corrVer()));
    }

    @Test
    public void checkSeveralErrorsInText() {
        List<beans.YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .texts(SEVERAL_ERRORS.wrongVer(), EN_WORD.wrongVer())
                        .callApi());

        assertThat("Incorrect Error", answer.get(0).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(0).s, hasItem(SEVERAL_ERRORS1.corrVer()));
        assertThat("Wrong suggestion", answer.get(1).s, hasItem(SEVERAL_ERRORS.corrVer()));

        assertThat("Incorrect Error", answer.get(2).code, equalTo(ERROR_UNKNOWN_WORD.errorCode));
        assertThat("Wrong suggestion", answer.get(2).s, hasItem(EN_WORD.corrVer()));
    }

    @Test
    public void checkCorrectTexts() {
        List<beans.YandexSpellerAnswer> answer =
                YandexSpellerApiCheckTexts.getYandexSpellerAnswers(YandexSpellerApiCheckTexts.with()
                        .texts(SEVERAL_ERRORS.corrVer(), EN_WORD.corrVer())
                        .callApi());

        assertThat("Response is wrong", answer.size(), equalTo(0));

    }
}