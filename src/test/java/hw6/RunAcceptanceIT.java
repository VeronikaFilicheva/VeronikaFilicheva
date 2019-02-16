package hw6;

import com.codeborne.selenide.Browsers;
import com.codeborne.selenide.Configuration;
import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.BeforeSuite;

    @CucumberOptions(
            features = "classpath:hw6",
            glue = "classpath:hw6.steps"
    )

public class RunAcceptanceIT extends AbstractTestNGCucumberTests {

        @BeforeSuite()
        public void driverSetUp() {
            Configuration.browser = Browsers.CHROME;
            Configuration.startMaximized = true;
            Configuration.timeout = 5000;
            Configuration.pollingInterval = 300;
        }

    }