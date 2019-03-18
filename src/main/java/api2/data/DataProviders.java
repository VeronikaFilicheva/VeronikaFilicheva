package api2.data;

import org.testng.annotations.DataProvider;

import java.util.Arrays;
import java.util.List;

import static api2.core.constants.Languages.*;

public class DataProviders {

    @DataProvider
    public Object[][] unknownWordError() {
        return new Object[][]{
                {new String[]{"cucumbar", "motherr", "fatherr"},
                        EN,
                        new List[]{Arrays.asList("cucumber", "cucumbers", "cucumba"),
                                Arrays.asList("mother", "mothers", "other"),
                                Arrays.asList("father", "further", "farther")}}
        };
    }

    @DataProvider
    public Object[][] incorrectSpacing() {
        return new Object[][]{
                {new String[]{"testis good", "beautifullbuilding", "functionaltest"},
                        EN,
                        new List[]{Arrays.asList("test is", "tastes", "tests"),
                                Arrays.asList("beautiful building", "beautifulbuildings", "beautiful buildings"),
                                Arrays.asList("functional test", "functional tests")}}
        };
    }


    @DataProvider
    public Object[][] repeatWords() {
        return new Object[][]{
                {new String[]{"testis good good", "beautifull building building"},
                        EN,
                        new List[]{Arrays.asList("good"),
                                Arrays.asList("building")}
                },
                {new String[]{"отличная погода погода", "красивое здание здание"},
                        RU,
                        new List[]{Arrays.asList("погода"),
                                Arrays.asList("здание")}
                }
        };
    }

    @DataProvider
    public Object[][] digitAndCapitalization() {
        return new Object[][]{
                {new String[]{"москв3а", "санкт-петербург2"},
                        RU,
                        new List[]{Arrays.asList("Москва"),
                                Arrays.asList("Санкт-Петербург")}
                }
        };

    }

    @DataProvider
    public Object[][] wordsWithDigits() {
        return new Object[][]{
                {new String[]{"москв3а", "санкт-петербург2"},
                        RU,
                        new List[]{Arrays.asList("москва", "москвва", "москву"),
                                Arrays.asList("петербург 2", "петербурге 2", "петербурга 2")}
                }
        };

    }

    @DataProvider
    public Object[][] incorrectRuWord() {
        return new Object[][]{
                {new String[]{"test прошол", "weather харошая"},
                        EN,
                        new List[]{Arrays.asList("прошел"),
                                Arrays.asList("хорошая")}
                }
        };
    }

    @DataProvider
    public Object[][] ruEnTexts() {
        return new Object[][]{
                {new String[]{"test is imotant ", "погода харошая"},
                        new List[]{Arrays.asList("important", "imortant", "impotant"),
                                Arrays.asList("хорошая")}
                }
        };
    }

    @DataProvider
    public Object[][] severalErrors() {
        return new Object[][]{
                {new String[]{"motherr is imotant ", "пjгода харошая"},
                        new List[]{Arrays.asList("mother","motherr", "mothers", "other"),
                                Arrays.asList("погода", "пагода"),
                                Arrays.asList(" important"),
                                Arrays.asList("хорошая", "харошая")}
                }
        };
    }


}

