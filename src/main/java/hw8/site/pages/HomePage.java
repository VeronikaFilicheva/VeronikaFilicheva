package hw8.site.pages;

import com.epam.jdi.light.elements.composite.WebPage;
import com.epam.jdi.light.elements.pageobjects.annotations.FindBy;
import com.epam.jdi.light.ui.html.common.Icon;
import com.epam.jdi.light.ui.html.complex.Menu;
import hw8.entities.User;
import hw8.enums.MenuEnum;
import hw8.site.forms.LoginForm;
import hw8.site.sections.Header;

import static org.testng.AssertJUnit.assertEquals;

public class HomePage extends WebPage {

    private LoginForm loginForm;
    public static Header header;

    @FindBy(css = "[id='user-icon']")
    private Icon loginIcon;

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

