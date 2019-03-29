package tests;

import helpers.ConfigLoader;
import helpers.EcofoodFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.DataHandler;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public abstract class Hooks {

    protected volatile Map<String, WebDriver> webDriverMap = new HashMap<>();


    @BeforeMethod
    public void setup(Method method) {
//        String testName = method.getName();
//        String sauceLabs = "http://"
//                + ConfigLoader.getValueByKey("username") + ":"
//                + ConfigLoader.getValueByKey("accessKey") +
//                "@ondemand.saucelabs.com:80/wd/hub";
//        DesiredCapabilities caps = DesiredCapabilities.chrome();
//        caps.setCapability("platform", "Windows 10");
//        caps.setCapability("version", "72.0");
//        caps.setCapability("name", testName);
//        try {
//            webDriver = new RemoteWebDriver(new URL(sauceLabs), caps);
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        }

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setHeadless(true);
        System.out.println(Thread.currentThread().getId());
        System.out.println(method.getName());
        WebDriver webDriver = new ChromeDriver(chromeOptions);
        webDriver.get(ConfigLoader.getValueByKey("URL"));
        webDriver.manage().window().maximize();
        webDriverMap.put(method.getName(), webDriver);

        DataHandler.dataRead("data.txt", DataHandler.DataType.DATA);
    }


    @AfterMethod
    public void afterMethod(ITestResult result, Method method) {
        if (ITestResult.FAILURE == result.getStatus()) {
            new EcofoodFacade(webDriverMap.get(method.getName())).takePhoto("error", result.getName());
        }
        // ((JavascriptExecutor)webDriver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));

        webDriverMap.get(method.getName()).quit();
    }

    protected EcofoodFacade setEcofoodFacade(String testName) {
        return new EcofoodFacade(webDriverMap.get(testName));
    }
}
