package api2.core;

import api2.core.constants.Languages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class RequestParameters {

    public RequestParameters(HashMap<String, Object> params ) {params=this.params;}

    public HashMap<String, Object> params = new HashMap<>();

    public HashMap<String, Object> addParameter(String paramName, Object Param) {
        params.put(paramName, Param);
        return params;
    }

    public HashMap<String, Object> addParameterStringList(String paramName, String... texts) {
        List<String> textsList = Arrays.asList(texts);
        params.put(paramName, textsList);
        return params;
    }

    public HashMap<String, Object> addParameterInt(String paramName, int option) {
        params.put(paramName,Integer.toString(option));
        return params;
    }

    public HashMap<String, Object> addParameterLanguage(String paramName, Languages... languages) {
        List<String> languagesList = new ArrayList<>();
        for (Languages language : languages) {
            languagesList.add(language.getLang());
        }
        String newLanguageList = String.join(", ", languagesList);
        params.put(paramName, languagesList);
        return params;
    }

}

