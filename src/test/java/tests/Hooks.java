package tests;

import helpers.ConfigLoader;
import helpers.EcofoodFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.DataHandler;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Hooks {
    protected WebDriver webDriver;
    protected EcofoodFacade ecofoodFacade;

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

        webDriver = new ChromeDriver();
        webDriver.get(ConfigLoader.getValueByKey("URL"));
        webDriver.manage().window().maximize();
        DataHandler.dataRead("data.txt", DataHandler.DataType.DATA);
        ecofoodFacade = new EcofoodFacade(webDriver);

    }


    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (ITestResult.FAILURE == result.getStatus()) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd HH_mm_ss");
            Date date = new Date();
            ecofoodFacade.takePhoto(dateFormat.format(date), result.getName());
        }
        // ((JavascriptExecutor)webDriver).executeScript("sauce:job-result=" + (result.isSuccess() ? "passed" : "failed"));

        webDriver.quit();
    }
}
