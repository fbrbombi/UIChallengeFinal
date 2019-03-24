package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

import static utilities.RandomGenerator.generateRandomNumber;

public class EcofoodMainPage extends EcofoodBasePage {

    @FindBy(xpath = "//*[@class='col-md-3 col-xs-6 product-1 miso-prd-holder']")
    private List<WebElement> productMatrix;

    private List<WebElement> productsCart;
    private List<Integer> idProducts;
    private List<String> nameProducts;
    private int idProduct;
    private WebElement product;
    private String total;
    private By add = By.xpath("//a[@class='fa fa-plus']");
    private By titleProduct = By.xpath("//h3[@class='title']");
    private By totalProduct = By.xpath("//div[@class='miso-prd-total']");
    private By decreaseQuantity = By.xpath("//a[@class='fa fa-minus']");
    private By removeProduct = By.xpath("//a[@class='fa fa-remove']");

    public EcofoodMainPage(WebDriver webDriver) {
        super(webDriver);
        productsCart = new ArrayList<>();
        idProducts = new ArrayList<>();
        nameProducts = new ArrayList<>();
    }

    public void addProduct() {
        driverFacade.waitUntilThePageIsLoaded();
        getWeekProduct();
        product.click();
        putProduct();
    }

    private void getWeekProduct() {
        driverFacade.waitUntilThePageIsLoaded();
        driverFacade.waitForVisibilityOfAllElements(productMatrix);
        idProduct = generateRandomNumber((productMatrix.size() - 1), 0);
        product = productMatrix.get(idProduct);
    }

    private void putProduct() {
        driverFacade.clickElement(product.findElement(add));
        setIdProductsAdded(idProduct);
        setNameProductsAdded(product.findElement(titleProduct).getText());
        productsCart.add(product);
    }


    public int getDifferenceTotalCartAndProductsAdded() {
        return Integer.parseInt(totalCart.getText()) - productsCart.size();
    }

    public void addAllStock() {
        getWeekProduct();
        product.findElement(totalProduct).click();
//        driverFacade.moveToElement(product.findElement(totalProduct));
        driverFacade.waitForVisibility(product.findElement(totalProduct));
        total = product.findElement(totalProduct).getText();
        product.click();
        for (int i = 0; i < Integer.parseInt(total); i++) {
            putProduct();
            System.out.println(i + 1);
        }
    }

    public int getStockOfTheProduct() {
        return Integer.parseInt(total);
    }

    public int getTotalCart() {
        driverFacade.waitForVisibility(totalCart);
        return Integer.parseInt(totalCart.getText());
    }

    public void decreaseQuantityOfaProduct() {
        product.click();
        driverFacade.clickElement(product.findElement(decreaseQuantity));
    }

    private void setIdProductsAdded(int idProduct) {
        if (!idProducts.contains(idProduct)) {
            idProducts.add(idProduct);
        }
    }

    private void setNameProductsAdded(String name) {
        if (!nameProducts.contains(name)) {
            nameProducts.add(name);
        }
    }

    public List<String> getNameProductsAdded() {
        return nameProducts;
    }

    public void emptyCart() {
        for (WebElement product : productsCart) {
            product.click();
            driverFacade.clickElement(product.findElement(decreaseQuantity));
        }
    }

    public String getHeaderText() {
        driverFacade.waitForVisibility(miniCart);
        return header.get(1).getText();
    }

    public EcofoodLogin goToLoginWebPage() {
        driverFacade.waitUntilThePageIsLoaded();
        login.click();
        return PageFactory.initElements(webDriver, EcofoodLogin.class);
    }

    public EcofoodRegister goToRegisterWebPage() {
        registrarse.click();
        return PageFactory.initElements(webDriver, EcofoodRegister.class);
    }

}



