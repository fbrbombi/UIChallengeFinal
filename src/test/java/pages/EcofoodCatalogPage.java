package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import utilities.GetterNumbers;

import java.util.ArrayList;
import java.util.List;

import static utilities.RandomGenerator.generateRandomNumber;

public class EcofoodCatalogPage extends EcofoodBasePage {
    @FindBy(xpath = "//a[@id='category0']")
    private WebElement frutasButton;

    @FindBy(xpath = "//a[@id='category1']")
    private WebElement verdurasButton;

    @FindBy(xpath = "//span[@class='totals']")
    private WebElement totalcart;

    @FindBy(xpath = "//div[@class='modal-body']")
    private WebElement productDetail;

    @FindBy(xpath = "//*[@class='col-md-4 col-sm-6 product-1 miso-prd-holder']")
    private List<WebElement> catalogProductMatrix;

    @FindBy(xpath = "//*[@class='col-md-4 col-sm-6 product-1 miso-prd-holder']//h3")
    private WebElement titleProduct;

    @FindBy(xpath = "//*[@class='col-md-4 col-sm-6 product-1 miso-prd-holder']//p")
    private WebElement priceProduct;

    @FindBy(xpath = "//*[@id='product-detail-name']")
    private WebElement titleDetail;

    @FindBy(xpath = "//*[@id='product-detail-price']")
    private WebElement priceDetail;

    private WebElement product;
    private List<WebElement> productsCart;
    private String title;
    private String price;
    private By add = By.xpath("//a[@class='fa fa-plus']");
    private By search = By.xpath("//a[@class='fa fa-search-plus']");

    public EcofoodCatalogPage(WebDriver webDriver) {
        super(webDriver);
        productsCart = new ArrayList<>();

    }

    public void addCatalogProduct() {
        driverFacade.waitUntilThePageIsLoaded();
        moveToaProduct();
        driverFacade.clickElement(product.findElement(add));
        productsCart.add(product);
    }

    private void moveToaProduct() {
        driverFacade.clickElement(frutasButton);
        chooseACatalogProduct();
        product.click();
    }

    private void chooseACatalogProduct() {
        driverFacade.waitForVisibilityOfAllElements(catalogProductMatrix);
        int idProduct = generateRandomNumber((catalogProductMatrix.size() - 1), 0);
        product = catalogProductMatrix.get(idProduct);
    }

    public void seeProductsDetails() {
        driverFacade.waitUntilThePageIsLoaded();
        moveToaProduct();
        driverFacade.waitForVisibility(titleProduct);
        title = titleProduct.getText();
        price = GetterNumbers.getNumbers(priceProduct.getText());
        driverFacade.clickElement(product.findElement(search));
        driverFacade.waitForVisibility(productDetail);

    }

    public boolean isCorrectPriceAndTitle() {
        boolean result1 = title.equals(titleDetail.getText());
        String price2 = GetterNumbers.getNumbers(priceDetail.getText());
        boolean result2 = price.equals(price2);
        return result1 && result2;
    }

    public int getDifferenceTotalCartAndProductsAdded() {
        return Integer.parseInt(totalCart.getText()) - productsCart.size();
    }


}
