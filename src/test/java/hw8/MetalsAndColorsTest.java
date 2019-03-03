package hw8;

import base.TestInitJDI8;
import hw8.data.MetalColor;
import hw8.data.MetalsAndColorsDataProvider;
import org.testng.annotations.Test;

import static hw8.entities.User.PITER_CHAILOVSKII;
import static hw8.enums.MenuEnum.METALS_AND_COLORS;
import static hw8.site.JDITestSite.homepage;
import static hw8.site.JDITestSite.metalsAndColors;

public class MetalsAndColorsTest extends TestInitJDI8 {

    @Test(dataProvider = "simpleDataProvider", dataProviderClass = MetalsAndColorsDataProvider.class)
    public void simpleJdiTest(MetalColor data) {

        //1. Login on jdi site as User
        homepage.open();
        homepage.login(PITER_CHAILOVSKII);
        homepage.checkLoggedUser(PITER_CHAILOVSKII);

        //2. Open Metals & Colors page by Header menu
        homepage.openHeaderMenuItem(METALS_AND_COLORS);

        //3. Fill form Metals & Colors by data below:
        metalsAndColors.fillForm(data);

        //4. Submit form Metals & Colors
        metalsAndColors.clickSubmitButton();

        //5 Result sections should contains data  below
        metalsAndColors.check(data);

    }
}
