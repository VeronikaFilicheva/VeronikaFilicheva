package lesson1;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static java.lang.System.setProperty;

public class SimpleTest {

    @BeforeClass
    public void beforeClass(){
        setProperty("webdriver.chrome.driver","src\\main\\resources\\chromedriver.exe");
    }

    @Test
    public void simpleTest(){
        WebDriver driver=new ChromeDriver();

        //1
        driver.manage().window().maximize();

        //2
        driver.navigate().to("https://epam.github.io/JDI/index.html");

        //3
        Assert.assertEquals(driver.getTitle(),"Home Page");

        //4
        driver.findElement(By.cssSelector("[id='user-icon']")).click();
        driver.findElement(By.cssSelector("[id='name']")).sendKeys("epam");
        driver.findElement(By.cssSelector("[id='password']")).sendKeys("1234");
        driver.findElement(By.cssSelector("[id='login-button']")).click();
        //
       // driver.close();
    }
}
