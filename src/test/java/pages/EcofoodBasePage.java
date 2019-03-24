package pages;

import helpers.DriverFacade;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EcofoodBasePage {
    protected DriverFacade driverFacade;
    protected WebDriver webDriver;

    @FindBy(xpath = "//div[@class='cart-shopping js-mini-shopcart']")
    protected WebElement miniCart;

    @FindBy(xpath = "//div[@class='header-login']//a")
    protected List<WebElement> header;

    @FindBy(xpath = "//i[@class='fa fa-shopping-cart']")
    protected WebElement shoppingCart;

    @FindBy(xpath = "//div[@class='mini-shopcart-action']")
    protected WebElement checkoutCart;

    @FindBy(xpath = "//button[@class='au-btn au-btn-primary au-btn-radius btn-checkout']")
    protected WebElement checkoutButton;

    @FindBy(xpath = "//a[@href='/paymentMethods/']")
    protected WebElement paymentMethod;

    @FindBy(xpath = "//a[@href='/orders']")
    protected WebElement orders;

    @FindBy(xpath = "//a[@href='/catalogo/']")
    protected WebElement catalog;

    @FindBy(xpath = "//span[@class='totals']")
    protected WebElement totalCart;

    @FindBy(xpath = "//a[@href='/login/']")
    protected WebElement login;

    @FindBy(xpath = "//a[@href='/regUser/']")
    protected WebElement registrarse;

    @FindBy(xpath = "//a[@href='/logout/']")
    protected WebElement logout;

    @FindBy(xpath = "//a[@href='/']")
    protected WebElement main;


    public EcofoodBasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        driverFacade = new DriverFacade(webDriver);
    }

    public EcofoodMainPage goToHomeWebPage() {
        driverFacade.waitUntilThePageIsLoaded();
        driverFacade.clickElement(main);
        return PageFactory.initElements(webDriver, EcofoodMainPage.class);
    }

    public EcofoodCheckoutPage goToCheckoutPage() {
        driverFacade.waitForVisibility(shoppingCart);
        driverFacade.clickElement(shoppingCart);
        driverFacade.waitForVisibility(checkoutCart);
        driverFacade.waitForVisibility(checkoutButton);
        driverFacade.clickElement(checkoutButton);
        driverFacade.waitUntilThePageIsLoaded();
        return PageFactory.initElements(webDriver, EcofoodCheckoutPage.class);
    }

    public EcofoodPayMethodPage goToPaymentMethodWebPage() {
        driverFacade.waitForVisibility(paymentMethod);
        driverFacade.clickElement(paymentMethod);
        return PageFactory.initElements(webDriver, EcofoodPayMethodPage.class);
    }

    public EcofoodCatalogPage goToCatalogWebPage() {
        driverFacade.waitForVisibility(catalog);
        driverFacade.clickElement(catalog);
        return PageFactory.initElements(webDriver, EcofoodCatalogPage.class);
    }

    public EcofoodOrderPage goToOrderPage() {
        driverFacade.clickElement(orders);
        return PageFactory.initElements(webDriver, EcofoodOrderPage.class);
    }

    public void takePhoto(String name, String folder) {
        driverFacade.takeScreenshot(name, folder);
    }
}
