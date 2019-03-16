package api2.core;

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

import static api2.core.constants.SoapActions.CHECK_TEXTS;
import static api2.core.constants.YandexSpellerConstants.*;
import static org.hamcrest.Matchers.lessThan;

//todo очень напоминает ранее сданную работу другого студента 
//разбей на 3 класса -  
//ОДИН - класс с проверкой результата, где будут храниться методы, о которых я написала в комментариях 
//к тестам (verifySuccess(), verifyCorrectHints() и т.д.) 
//ВТОРОЙ - формирование параметров запроса. с парой осмысленных методов, которые будут возвращать HashMap<String, Object> params. 
// постарайся сделать класс расширяемым (на случай, если количество параметров может увеличиться) и 
//расширяемым (на предмет возможности использовать его с другими сервисами) 
//ТРЕТИЙ - ResponseSpecification и вызов самого метода GET. callApi название метода непонятное. 
//эндпоитов может быть много у сервиса, так что какой конкретно - непонятно.
public class YandexSpellerApiCheckTexts {

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
                    .get(YANDEX_SPELLER_API_URI.concat(CHECK_TEXTS.getMethod())).prettyPeek();
        }
    }

    public static ApiBuilder with() {
        YandexSpellerApiCheckTexts api = new YandexSpellerApiCheckTexts();
        return new ApiBuilder(api);
    }

    public static List<beans.YandexSpellerAnswer> getYandexSpellerAnswers(Response response) {
        List<List<beans.YandexSpellerAnswer>> answers =  new Gson().fromJson(response.asString().trim(), new TypeToken<List<List<beans.YandexSpellerAnswer>>>() {
        }.getType());
        List<beans.YandexSpellerAnswer> finalAnswer = new ArrayList<>();
        for(List<beans.YandexSpellerAnswer> answer: answers){
            finalAnswer.addAll(answer);
        }
        return finalAnswer;
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
                .setBaseUri(YANDEX_SPELLER_API_URI.concat(CHECK_TEXTS.getMethod()))
                .build();
    }
}
