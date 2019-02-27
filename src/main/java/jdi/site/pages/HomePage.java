package jdi.site.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.ui.html.common.Icon;
import com.epam.jdi.light.ui.html.common.Link;
import com.epam.jdi.light.ui.html.complex.Menu;
import jdi.entities.User;
import jdi.enums.MenuEnum;
import jdi.site.forms.LoginForm;
import jdi.site.sections.Header;

import static org.testng.AssertJUnit.assertEquals;

public class HomePage extends WebPage {

    LoginForm loginForm;
    public static Header header;

    @FindBy(css = "[id='user-icon']")
    private Icon loginIcon;

    @FindBy(css = "[class = 'uui-navigation nav navbar-nav m-l8'] [href='metals-colors.html']")
    public Link MetalsAndColors;

    @FindBy(xpath = "//ul[@class='sidebar-menu'] //span[text()='%s']")
    public Menu menu;

    public void openHeaderMenuItem(MenuEnum page) {
        menu.select(page.menuButton);
    }

    public void login(User user) {
        loginIcon.click();
        loginForm.login(user);
    }

    public void checkLoggedUser(User user) {
        assertEquals(header.profilePhoto.getText(), user.getUserName());
    }


}

