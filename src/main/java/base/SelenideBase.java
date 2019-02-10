package base;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;

public abstract class SelenideBase {
    private long time;

    @BeforeSuite
    public void beforeSuite() {

        Configuration.browser = Browsers.CHROME;
        Configuration.startMaximized = true;

    }
}
