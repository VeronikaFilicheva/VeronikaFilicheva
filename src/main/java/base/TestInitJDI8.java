package base;

import com.epam.jdi.light.driver.WebDriverFactory;
import hw8.site.JDITestSite;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.light.ui.html.PageFactory.initElements;

public class TestInitJDI8 {
    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        initElements(JDITestSite.class);

    }

    @AfterMethod
    public void afterSuite() {
        WebDriverFactory.close();
    }
}

