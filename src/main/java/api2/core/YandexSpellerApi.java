package api2.core;

import api2.beans.YandexSpellerAnswer;
import api2.core.constants.Languages;
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


import java.util.HashMap;
import java.util.List;
import java.util.stream.IntStream;

import static api2.core.constants.SoapActions.*;
import static api2.core.constants.YandexSpellerConstants.*;
import static org.hamcrest.Matchers.lessThan;

public class YandexSpellerApi {

    //Parameters
    private HashMap<String, Object> params = new HashMap<>();
    private RequestParameters parameters = new RequestParameters(params);

    // Specs
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
                .setAccept(ContentType.JSON)
                .setRelaxedHTTPSValidation()
                .setBaseUri(YANDEX_SPELLER+SPELLER_JSON +CHECK_TEXTS.getMethod())
                .build();
    }

    // answers
    public static List<List<YandexSpellerAnswer>> getYandexSpellerAnswers(Response response) {
        return new Gson().fromJson(response.asString().trim(), new TypeToken<List<List<YandexSpellerAnswer>>>() {
        }.getType());
    }

    public static YandexSpellerApi.ApiBuilder with() {
        YandexSpellerApi api = new YandexSpellerApi();
        return new YandexSpellerApi.ApiBuilder(api);
    }

    public static class ApiBuilder {
        YandexSpellerApi spellerApi;

        private ApiBuilder(YandexSpellerApi gcApi) {
            spellerApi = gcApi;
        }

        public ApiBuilder texts(String... texts) {
            spellerApi.parameters.addParameterStringList(PARAM_TEXTS, texts);
            return this;
        }

        public ApiBuilder options(int... options) {
            spellerApi.parameters.addParameterInt(PARAM_OPTIONS, IntStream.of(options).sum());
            return this;
        }

        public ApiBuilder language(Languages... languages) {
            spellerApi.parameters.addParameterLanguage(PARAM_LANGUAGES, languages);
            return this;

        }

        public Response getCheckTexts() {
            return RestAssured.with()
                    .queryParams(spellerApi.parameters.params)
                    .log().all()
                    .get(YANDEX_SPELLER+SPELLER_JSON +CHECK_TEXTS.getMethod()).prettyPeek();
        }

        public Response getCheckText() {
            return RestAssured.with()
                    .queryParams(spellerApi.parameters.params)
                    .log().all()
                    .get(YANDEX_SPELLER+SPELLER_JSON +CHECK_TEXTS.getMethod()).prettyPeek();
        }
    }

}





