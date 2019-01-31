package base;

import org.testng.annotations.BeforeSuite;
import org.openqa.selenium.WebDriver;

import static java.lang.System.setProperty;

public abstract class SeleniumBase {
    protected WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite(){
        setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
    }
}


