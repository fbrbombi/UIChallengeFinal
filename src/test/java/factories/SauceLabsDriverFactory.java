package factories;

import helpers.ConfigLoader;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class SauceLabsDriverFactory implements DriverFactory {
    @Override
    public Capabilities setCapabilities(String testName) {
        DesiredCapabilities caps = DesiredCapabilities.chrome();
        caps.setCapability("platform", "Windows 10");
        caps.setCapability("version", "72.0");
        caps.setCapability("name", testName);
        return caps;
    }


    @Override
    public WebDriver createWebDriver(String testName) {
        String sauceLabs = "http://"
                + ConfigLoader.getValueByKey("username") + ":"
                + ConfigLoader.getValueByKey("accessKey") +
                "@ondemand.saucelabs.com:80/wd/hub";

        try {
            return new RemoteWebDriver(new URL(sauceLabs), setCapabilities(testName));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public WebDriver createWebDriver() {
        return null;
    }
}
