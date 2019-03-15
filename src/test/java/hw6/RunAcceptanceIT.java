package hw6;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.codeborne.selenide.Selenide.close;
import static com.codeborne.selenide.Selenide.open;

@CucumberOptions(
        features = "classpath:hw6",
        glue = "classpath:hw6.steps"
    )

public class RunAcceptanceIT extends AbstractTestNGCucumberTests {

    private static final String INDEX_PAGE_URL = "https://epam.github.io/JDI/index.html";


        @BeforeMethod()
        public void driverSetUp() {
            System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
            Configuration.browser = Browsers.CHROME;
            Configuration.startMaximized = true;
            Configuration.timeout = 5000;
            Configuration.pollingInterval = 300;
            open(INDEX_PAGE_URL);
        }

        @AfterMethod
        public void afterMethod() {
            close();
        }

    }