package pages;

import helpers.ConfigLoader;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.RandomGenerator;
import utilities.ScreenShotManager;

public class EcofoodRegister extends EcofoodBasePage {

    @FindBy(xpath = "//input[@id='name']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='lastname']")
    private WebElement lastname;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='email']")
    private WebElement email;

    @FindBy(xpath = "//input[@id='pass1']")
    private WebElement password1;

    @FindBy(xpath = "//input[@id='pass2']")
    private WebElement password2;

    @FindBy(xpath = "//button[@id='register']")
    private WebElement registrarseButton;

    private String alertText;

    public EcofoodRegister(WebDriver webDriver) {
        super(webDriver);
    }

    public void fillForm() {
        driverFacade.waitUntilThePageIsLoaded();
        name.sendKeys(ConfigLoader.getValueByKey("nameR"));
        lastname.sendKeys(ConfigLoader.getValueByKey("lastnameR"));
        username.sendKeys(RandomGenerator.generateRandomString(RandomGenerator.generateRandomNumber(10, 0)));
        email.sendKeys(ConfigLoader.getValueByKey("emailR"));
        password1.sendKeys(ConfigLoader.getValueByKey("passR"));
        password2.sendKeys(ConfigLoader.getValueByKey("pass2R"));
        driverFacade.clickElement(this.registrarseButton);
        setAlertText();
    }

    private void setAlertText() {
        driverFacade.waitForAlert();
        ScreenShotManager.takeAlertScreenshot();
        Alert alert = driverFacade.switchToAlert();
        alertText = alert.getText();
        alert.accept();
        driverFacade.switchToElement();
    }

    public String getAlert() {
        return alertText;
    }


}
