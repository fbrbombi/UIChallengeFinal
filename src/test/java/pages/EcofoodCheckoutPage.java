package pages;

import helpers.ConfigLoader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utilities.DataHandler;

import java.util.List;

import static utilities.RandomGenerator.generateRandomNumber;

public class EcofoodCheckoutPage extends EcofoodBasePage {
    @FindBy(xpath = "//*[@id='purchase']")
    private WebElement purchase;

    @FindBy(xpath = "//div[@class='modal-content']")
    private WebElement emergentWindow;

    @FindBy(xpath = "//div[@class='col-md-6']//input[@id]")
    private List<WebElement> formFields;

    @FindBy(xpath = "//select[@id='country']")
    private WebElement country;

    @FindBy(xpath = "//select[@id='dpto']")
    private WebElement dpto;

    @FindBy(xpath = "//div[@class='modal-footer']//button[@type='button']")
    private WebElement thanksButton;

    @FindBy(xpath = "//h5[@id='paymentm']")
    private WebElement titleCheckout;

    @FindBy(xpath = "//div[@class='introduce']//input[@type='radio']")
    private List<WebElement> paymentList;

    @FindBy(xpath = "//div[@class='checkout-payment-info']//div[@class='input-row m-b-15']//input[@id]")
    private List<WebElement> paymentForm;

    String title;

    public EcofoodCheckoutPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void checkoutWithoutPaymentMethod() {
        fillForm();
        paymentForm();
        driverFacade.clickElement(purchase);
        driverFacade.waitForVisibility(emergentWindow);
    }

    public void checkoutFinalProcess() {
        title = titleCheckout.getText();
        driverFacade.waitForVisibility(thanksButton);
        driverFacade.clickElement(thanksButton);
    }

    public String getTitleResponse() {
        return title;
    }

    public void checkoutWithPaymentMethod() {
        fillForm();
        driverFacade.clickElement(paymentList.get(generateRandomNumber((paymentList.size() - 1), 0)));
        driverFacade.clickElement(purchase);
        driverFacade.waitForVisibility(emergentWindow);
    }

    private void fillForm() {
        driverFacade.waitUntilThePageIsLoaded();
        driverFacade.waitForVisibilityOfAllElements(formFields);
        DataHandler.putData(formFields, DataHandler.getData("c"));
        selectElement(country, ConfigLoader.getValueByKey("country"));
        selectElement(dpto, "Bogotá D.C");
    }

    private void paymentForm() {
        DataHandler.putData(paymentForm, DataHandler.getData("p"));
    }

    private void selectElement(WebElement element, String option) {
        new Select(element).selectByVisibleText(option);
    }


}
