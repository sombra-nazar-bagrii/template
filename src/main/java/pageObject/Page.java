package pageObject;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;
import java.util.Random;

/**
 * Created by sombra-15 on 12.09.17.
 */
public abstract class Page {

    /*
    В цьому класі мають бути описані елементи а також методи, які будуть спільними для усіх інших сторінок.
    Також цей клас є батьківським для усіх інших сторінок, які будуть створені.
     */

    protected WebDriver webDriver;

    protected Page(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    protected WebDriver getWebDriver() {
        return webDriver;
    }

    protected void openNewWindow(String URL) {
        webDriver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
        for (String winHandle : webDriver.getWindowHandles()) {
            System.out.println(winHandle);
            webDriver.switchTo().window(winHandle);
        }
        webDriver.get(URL);
    }

    protected void openNextTab() {
        String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL, Keys.TAB);
        webDriver.findElement(By.cssSelector("body")).sendKeys(selectLinkOpeninNewTab);
    }

    protected void clickOnElement(WebElement element) {
        element.click();
    }

    protected boolean isElementPresent(WebElement element) {
        try {
            element.isEnabled();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected boolean isElementDisplayed(WebElement element) {
        try {
            element.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    protected String containsAttribute(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    protected void customSendKeys(WebElement element, String text) {
        element.sendKeys(text);
    }

    protected void customClear(WebElement element) {
        element.clear();
    }

    protected void customClearAndSendValue(WebElement webel, String str) {
        webel.clear();
        webel.sendKeys(str);
    }

    protected void customSelectByVisibleText(WebElement element, String text) {
        new Select(element).selectByVisibleText(text);
    }

    protected void customSelectByIndex(WebElement element, int index) {
        new Select(element).selectByIndex(index);
    }

    protected void waitForElement(WebElement element, WebDriver driver, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOf(element));
    }


    protected void isTextAreTheSame(String actual, String expected) {
        Assert.assertTrue(actual.equals(expected));
    }

    protected String generateString() {
        char[] arr;
        final Random random = new Random();
        final int size = random.nextInt(10) + 5;
        arr = new char[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (char) (random.nextInt(25) + 65);
        }
        String value = new String(arr);
        return value.toLowerCase();
    }

    protected void customMoveToElement(WebElement ell) {
        Actions action = new Actions(webDriver);
        action.moveToElement(ell);
        action.perform();
    }

    protected void generateValuesForAll(List<WebElement> n) {
        for (WebElement count : n) {
            customClearAndSendValue(count, generateString());
        }
    }
}
