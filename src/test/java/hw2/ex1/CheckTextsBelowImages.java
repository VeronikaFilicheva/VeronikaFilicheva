package hw2.ex1;

import base.SeleniumBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CheckTextsBelowImages extends SeleniumBase{
    private WebDriver driver;

    @BeforeTest
    public void beforeTest(){
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterTest
    public void afterTest(){
    driver.close();
    }

    @DataProvider (name = "dataProviderMethod", parallel = true)
    private Object[][] dataProviderMethod() {
        return new Object[][]{
                {0, "To include good practices\n" +
                        "and ideas from successful\n" +
                        "EPAM project"},
                {1, "To be flexible and\n" +
                        "customizable"},
                {2, "To be multiplatform"},
                {3, "Already have good base\n" +
                        "(about 20 internal and\n" +
                        "some external projects),\n" +
                        "wish to get more…"}
        };
    }

    @Test(dataProvider = "dataProviderMethod")
    public void checkTextsBelowImages(int i, String s){

        //1 Open test site by URL
        driver.navigate().to("https://epam.github.io/JDI/");

        //2 Assert that there are 4 texts on the Index Page under icons and they have proper text
        List<WebElement> benefitTexts = driver.findElements(By.cssSelector(".benefit-txt"));
        assertEquals(benefitTexts.size(), 4);
        assertEquals(benefitTexts.get(i).getText(),s);

        }

}
