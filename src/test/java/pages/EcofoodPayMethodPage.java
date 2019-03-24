package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.DataHandler;

import java.util.List;

public class EcofoodPayMethodPage extends EcofoodBasePage {
    @FindBy(xpath = "//button[@id='list_payment_btn']")
    private WebElement addButton;

    @FindBy(xpath = "//div[@class='input-row m-b-15']")
    private WebElement methodForm;

    @FindBy(xpath = "//div[@class='input-row m-b-15']//input[@id]")
    private List<WebElement> paymentFields;

    @FindBy(xpath = "//button[@id='sub_new']")
    private WebElement addMethodButton;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement emergentWindow;

    @FindBy(xpath = "//div[@class='modal-footer']//button[@type='button']")
    private WebElement thanksButton;

    public EcofoodPayMethodPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void addNewPaymentMethod() {
        driverFacade.clickElement(addButton);
        driverFacade.waitForVisibility(methodForm);
        DataHandler.putData(paymentFields, DataHandler.getData("p"));
        driverFacade.clickElement(addMethodButton);
        driverFacade.waitForVisibility(emergentWindow);
        driverFacade.clickElement(thanksButton);
    }
}
