package hw8.data;

import base.JDITestsInit;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Type;
import java.util.Map;

public class MetalsAndColorsDataProvider extends JDITestsInit {
    @DataProvider
    public static Object[][] simpleDataProvider() throws FileNotFoundException {
        String jsonDataSetPath = "src/main/resources/JDI_ex8_metalsColorsDataSet.json";
        Gson gson = new Gson();
        JsonObject jsonData = new JsonParser().parse(new FileReader(jsonDataSetPath)).getAsJsonObject();

        Type type = new TypeToken<Map<String, MetalColor>>() {
        }.getType();
        Map<String, MetalColor> dataSet = gson.fromJson(jsonData, type);

        Object[][] dataObjects = new Object[dataSet.size()][1];
        int index = 0;
        for (String key : dataSet.keySet()) {
            dataObjects[index++][0] = dataSet.get(key);
        }
        return dataObjects;
    }
}