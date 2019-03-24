package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EcofoodOrderPage extends EcofoodBasePage {

    @FindBy(xpath = "//tbody[@id='orders']//tr[4]")
    private List<WebElement> details;

    @FindBy(xpath = "//table[@id='freeze-table']")
    private WebElement ordersTable;

    @FindBy(xpath = "//tbody[@id='orders']//td[1]")
    private List<WebElement> orders;

    @FindBy(xpath = "//tbody[@id='products']//td[1]")
    private List<WebElement> detailTable;

    private List<String> orderProductsAdded;
    private List<Integer> orderNumber;
    private String seeDetail = "']//a[text()='Ver detalle']";
    private String row = "//td[@id='";


    public EcofoodOrderPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void orderDetail() {
        driverFacade.waitForVisibility(ordersTable);
        driverFacade.waitForVisibilityOfAllElements(orders);
        sortTable();
        int greater = orderNumber.get(orderNumber.size() - 1);
        By details = By.xpath(row + greater + seeDetail);
        driverFacade.findElementUsingBy(details).click();
        getOrderProducts();
    }

    private void getOrderProducts() {
        driverFacade.waitForVisibilityOfAllElements(detailTable);
        orderProductsAdded = new ArrayList<>();
        for (WebElement element : detailTable) {
            orderProductsAdded.add(element.getText());
        }
    }

    public List<String> getOrderProductsAdded() {
        return orderProductsAdded;
    }

    private void sortTable() {
        orderNumber = new ArrayList<>();
        for (WebElement order : orders) {
            orderNumber.add(Integer.parseInt(order.getText()));
        }
        Collections.sort(orderNumber);
        System.out.println(orderNumber);
    }
}
