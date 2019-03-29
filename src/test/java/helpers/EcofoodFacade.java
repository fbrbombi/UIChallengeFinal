package helpers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.util.ArrayList;
import java.util.List;

import static utilities.RandomGenerator.generateRandomNumber;

public class EcofoodFacade {

    private EcofoodCatalogPage catalogPage;
    private EcofoodCheckoutPage checkoutPage;
    private EcofoodLogin loginPage;
    private EcofoodMainPage homePage;
    private List<String> nameProducts;
    private EcofoodRegister registerPage;
    private EcofoodPayMethodPage payMethodPage;
    private EcofoodOrderPage orderPage;

    public EcofoodFacade(WebDriver webDriver) {
        this.homePage = PageFactory.initElements(webDriver, EcofoodMainPage.class);
        nameProducts = new ArrayList<>();
    }

    public void goToRegisterPage() {
        registerPage = homePage.goToRegisterWebPage();
    }

    public void fillRegisterForm() {
        registerPage.fillForm();
    }

    public String getRegisterResponse() {
        return registerPage.getAlert();
    }

    public synchronized void goToLoginPage() {
        loginPage = homePage.goToLoginWebPage();
    }

    public void fillLoginForm() {
        homePage = loginPage.fillForm(ConfigLoader.getValueByKey("nameL"), ConfigLoader.getValueByKey("passL"));
    }

    public String verifyLoginResponse() {
        return homePage.getHeaderText();
    }

    public void fillLoginFormWithIncorrectData() {
        homePage = loginPage.fillForm(ConfigLoader.getValueByKey("nameW"), ConfigLoader.getValueByKey("passW"));
    }

    public String verifyUnsuccessfulLogin() {
        return loginPage.getMessage();
    }

    public void getLoggedOut() {
        homePage = loginPage.getLoggedOut();
    }

    public void userIsLoggedIn() {
        goToLoginPage();
        fillLoginForm();
    }

    public void addProductsFromHomePage() {
        for (int i = 0; i < generateRandomNumber(5, 1); i++) {
            homePage.addProduct();
        }
        nameProducts = homePage.getNameProductsAdded();
    }

    public int verifyProductsAddedFromHomePage() {
        return homePage.getDifferenceTotalCartAndProductsAdded();

    }

    public void goToCatalogPage() {
        catalogPage = homePage.goToCatalogWebPage();
    }

    public void addCatalogProducts() {
        for (int i = 0; i < generateRandomNumber(10, 1); i++) {
            catalogPage.addCatalogProduct();
        }
    }

    public int verifyProductsAddedFromCatalogPage() {
        return catalogPage.getDifferenceTotalCartAndProductsAdded();
    }

    public void addAllStock() {
        homePage.addAllStock();
    }

    public int verifyAllStockAdded() {
        int totalProduct = homePage.getStockOfTheProduct();
        int totalCart = homePage.getTotalCart();
        return totalCart - totalProduct;

    }

    public void decreaseProductQuantity() {
        homePage.decreaseQuantityOfaProduct();
    }

    public void emptyCart() {
        homePage.emptyCart();
    }

    public void productDetails() {
        catalogPage.seeProductsDetails();
    }

    public boolean isCorrectTheInformation() {
        return catalogPage.isCorrectPriceAndTitle();
    }

    public void goToCheckoutPageFromHomePage() {
        checkoutPage = homePage.goToCheckoutPage();
    }

    public void checkoutWithoutPaymentMethod() {
        checkoutPage.checkoutWithoutPaymentMethod();
    }

    public void checkoutWithPaymentMethod() {
        checkoutPage.checkoutWithPaymentMethod();
    }

    public void verifyCheckoutProcess() {
        checkoutPage.checkoutFinalProcess();
    }

    public String checkoutResponse() {
        return checkoutPage.getTitleResponse();
    }

    public void goToPaymentMethodPage() {
        payMethodPage = homePage.goToPaymentMethodWebPage();
    }

    public void addNewMethod() {
        payMethodPage.addNewPaymentMethod();
    }

    public void goToHomePage() {
        homePage = payMethodPage.goToHomeWebPage();
    }


    public void goToOrderPage() {
        orderPage = homePage.goToOrderPage();

    }

    public void seeDetailLastOrder() {
        orderPage.orderDetail();

    }

    public boolean verifyOrder() {
        return nameProducts.containsAll(orderPage.getOrderProductsAdded());
    }

    public int getTotalProductsAdded() {
        return homePage.getTotalCart();
    }

    public void takePhoto(String name, String fold) {
        homePage.takePhoto(name, fold);
    }
}
