package api2.core;

import api2.beans.YandexSpellerAnswer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.*;
import java.util.stream.IntStream;

import static api2.core.constants.YandexSpellerConstants.*;
import static org.hamcrest.Matchers.lessThan;


public class YandexSpellerApiCheckTexts {

    private YandexSpellerApiCheckTexts() {
    }

    private HashMap<String, Object> params = new HashMap<>();

    public static class ApiBuilder {
        YandexSpellerApiCheckTexts spellerApi;

        private ApiBuilder(YandexSpellerApiCheckTexts gcApi) {
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
            for (Languages language: languages){
                languagesList.add(language.getLang());
            }
            String newLanguageList = String.join(", ", languagesList);
            spellerApi.params.put(PARAM_LANGUAGES, newLanguageList);
            return this;
        }

        public Response callApi() {
            return RestAssured.with()
                    .queryParams(spellerApi.params)
                    .log().all()
                    .get(YANDEX_SPELLER_API_URI).prettyPeek();
        }
    }

    public static ApiBuilder with() {
        YandexSpellerApiCheckTexts api = new YandexSpellerApiCheckTexts();
        return new ApiBuilder(api);
    }

    public static List<YandexSpellerAnswer> getYandexSpellerAnswers(Response response) {
        List<List<YandexSpellerAnswer>> answersList =  new Gson().fromJson(response.asString().trim(), new TypeToken<List<List<YandexSpellerAnswer>>>() {
        }.getType());
        List<YandexSpellerAnswer> finalAnswerList = new ArrayList<>();
        for(List<YandexSpellerAnswer> answer: answersList){
            if(answer.size()<1){
                return finalAnswerList;
            }
            finalAnswerList.addAll(answer);
        }
        return finalAnswerList;
    }

    public static ResponseSpecification successResponse() {
        return new ResponseSpecBuilder()
                .expectContentType(ContentType.JSON)
                .expectHeader("Connection", "keep-alive")
                .expectResponseTime(lessThan(20000L))
                .expectStatusCode(HttpStatus.SC_OK)
                .build();
    }

    public static RequestSpecification baseRequestConfiguration() {
        return new RequestSpecBuilder()
                .setAccept(ContentType.XML)
                .setRelaxedHTTPSValidation()
                .addHeader("Dasha header", "header.value")
                .addQueryParam("RequestID", new Random().nextLong())
                .setBaseUri(YANDEX_SPELLER_API_URI)
                .build();
    }
}