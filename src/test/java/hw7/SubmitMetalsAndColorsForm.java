package hw7;

import base.JDITestsInit;
import jdi.data.MetalColor;
import org.testng.annotations.Test;

import static jdi.entities.User.PITER_CHAILOVSKII;
import static jdi.enums.MenuEnum.METALS_AND_COLORS;
import static jdi.site.JDITestSite.*;



public class SubmitMetalsAndColorsForm extends JDITestsInit {
    MetalColor data = new MetalColor();

    @Test
    public void simpleJdiTest() {
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