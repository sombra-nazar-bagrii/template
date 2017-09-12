package tests;

import factory.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

/**
 * Created by sombra-15 on 12.09.17.
 */
public class TestBase {

    private static WebDriver webDriver;
    private static final String URL = "https://sombrainc.com/";
    //protected Header startPage2;   // створюємо посилання на обєкти класів, які будуть загружені при відкритті лінки
    //protected HomePage startPage;

    @Parameters({ "browserName" })  // значення отримане з сют тесту
    @BeforeSuite
    public void init(String browserName) throws Exception {
        webDriver = WebDriverFactory.getInstance(browserName); //
        webDriver.get(URL);
    }
    @BeforeMethod
    public void includeExclude(){
        //    startPage = PageFactory.initElements(webDriver,HomePage.class);   // PageFactory.initElements(webDriver,HomePage.class) == new HomePage(webDriver)
        //   startPage2 = PageFactory.initElements(webDriver,Header.class);
    }
    @AfterMethod
    public void afterMethod(){webDriver.get(URL);  // після закінчення тестового методу повертаємось на початкову сторінку
    }
}