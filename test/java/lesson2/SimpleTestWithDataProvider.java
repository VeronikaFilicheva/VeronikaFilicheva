package lesson2;

import base.SeleniumBase;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SimpleTestWithDataProvider extends SeleniumBase{

    @DataProvider
    private Object[][] simpleDataProvider(){
      return new Object[][] {
            {0, "Ivam"},
            {1, "Peter"},
            {2,"Dima"}
        };

    }

    @Test (dataProvider = "simpleDataProvider")
    public void simpleTest(int i, String s){
        System.out.println("int i="+i+"\n"+"String="+s);
    }


}



