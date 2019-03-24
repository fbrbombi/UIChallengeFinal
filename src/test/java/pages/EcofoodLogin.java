package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EcofoodLogin extends EcofoodBasePage {
    @FindBy(xpath = "//input[@id='username']")
    private WebElement name;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//button[@id='login']")
    private WebElement loginButton;

    @FindBy(xpath = "//div[@class='modal fade in']")
    private WebElement unsuccessfulLogin;

    @FindBy(xpath = "//*[@id='exampleModalLongTitle']")
    private WebElement unsuccessfulTitle;

    @FindBy(xpath = "//button[@class='btn btn-danger']")
    private WebElement tryAgain;

    public EcofoodLogin(WebDriver webDriver) {
        super(webDriver);
    }

    public EcofoodMainPage fillForm(String name, String pass) {
        driverFacade.waitUntilThePageIsLoaded();
        this.name.sendKeys(name);
        this.password.sendKeys(pass);
        loginButton.click();
        return PageFactory.initElements(webDriver, EcofoodMainPage.class);
    }

    public String getMessage() {
        driverFacade.waitForVisibility(unsuccessfulLogin);
        String header = unsuccessfulTitle.getText();
        driverFacade.clickElement(tryAgain);
        return header;
    }

    public EcofoodMainPage getLoggedOut() {
        driverFacade.waitForVisibility(logout);
        driverFacade.clickElement(logout);
        return PageFactory.initElements(webDriver, EcofoodMainPage.class);
    }

}
