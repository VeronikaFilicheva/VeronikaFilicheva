package base;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

public abstract class SelenideBase {
    private long time;

    @BeforeSuite
    public void beforeSuite() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        Configuration.browser = Browsers.CHROME;
        Configuration.startMaximized = true;

    }
}
