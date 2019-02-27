package base;

import com.epam.jdi.light.driver.WebDriverFactory;
import jdi.site.JDITestSite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import static com.epam.jdi.light.ui.html.PageFactory.initElements;

public class JDITestsInit {

    @BeforeSuite(alwaysRun = true)
    public static void setUp() {
        initElements(JDITestSite.class);

    }

    @AfterSuite
    public void afterSuite() {
        WebDriverFactory.close();
    }
}
