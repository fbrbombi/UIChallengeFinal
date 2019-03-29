package factories;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;

public interface DriverFactory {

    Capabilities setCapabilities(String testName);

    WebDriver createWebDriver(String testName);

    WebDriver createWebDriver();
}
