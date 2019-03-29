package factories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class LocalDriverFactory implements DriverFactory {
    @Override
    public ChromeOptions setCapabilities(String testName) {
        return new ChromeOptions().setHeadless(true);

    }

    @Override
    public WebDriver createWebDriver(String testName) {
        return null;
    }

    @Override
    public ChromeDriver createWebDriver() {
        return new ChromeDriver(setCapabilities(null));
    }

}
