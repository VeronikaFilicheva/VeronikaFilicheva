package api2.core;

import api2.core.constants.Languages;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static api2.core.constants.SoapActions.*;
import static api2.core.constants.YandexSpellerConstants.*;

public class ApiBuilder {
    private HashMap<String, Object> params = new HashMap<>();

    YandexSpellerApi spellerApi;

    ApiBuilder(YandexSpellerApi gcApi) {
        spellerApi = gcApi;
    }

    public ApiBuilder texts(String... texts) {
        List<String> textsList = Arrays.asList(texts);
        spellerApi.params.put(PARAM_TEXTS, textsList);
        return this;
    }

    public ApiBuilder options(int... options) {
        spellerApi.params.put(PARAM_OPTIONS, Integer.toString(IntStream.of(options).sum()));
        return this;
    }

    public ApiBuilder language(Languages... languages) {
        List<String> languagesList = new ArrayList<>();
        for (Languages language : languages) {
            languagesList.add(language.getLang());
        }
        String newLanguageList = String.join(", ", languagesList);
        spellerApi.params.put(PARAM_LANGUAGES, newLanguageList);
        return this;
    }

    public Response getCheckTexts() {
        return RestAssured.with()
                .queryParams(spellerApi.params)
                .log().all()
                .get(YANDEX_SPELLER+SPELLER_JSON +CHECK_TEXTS.getMethod()).prettyPeek();
    }

    public Response getCheckText() {
        return RestAssured.with()
                .queryParams(spellerApi.params)
                .log().all()
                .get(YANDEX_SPELLER+SPELLER_JSON +CHECK_TEXTS.getMethod()).prettyPeek();
    }
}