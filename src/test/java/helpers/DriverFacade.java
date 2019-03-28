package helpers;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ScreenShotManager;

import java.util.List;

public class DriverFacade {
    protected WebDriver webDriver;

    private By loader = By.xpath("//div[@class='loader']");

    public DriverFacade(WebDriver webDriver) {
        this.webDriver = webDriver;
    }


    public synchronized void clickElement(WebElement webElement) {
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].click()", webElement);
    }

    public synchronized void waitForVisibility(WebElement webElement) {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOf(webElement));

    }

    public synchronized void waitForVisibilityOfAllElements(List<WebElement> list) {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.visibilityOfAllElements(list));

    }

    public synchronized void waitUntilThePageIsLoaded() {
        new WebDriverWait(webDriver, 10).until(ExpectedConditions.invisibilityOfElementLocated(loader));
    }

    public synchronized void waitForAlert() {
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.alertIsPresent());
    }


    public synchronized void takeScreenshot(String name, String directory) {
        ScreenShotManager.takeScreenShotTest(this.webDriver, name, directory);
    }

    public synchronized Alert switchToAlert() {
        return webDriver.switchTo().alert();
    }

    public synchronized void switchToElement() {
        webDriver.switchTo().activeElement();
    }

    public synchronized WebElement findElementUsingBy(By path) {
        return webDriver.findElement(path);
    }
}
