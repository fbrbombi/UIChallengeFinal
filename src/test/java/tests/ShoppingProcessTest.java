package tests;

import helpers.EcofoodFacade;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ShoppingProcessTest extends Hooks {
    EcofoodFacade ecofoodFacade;
    @Test
    public void AddProductsFromHomePage() {

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        int productsAdded = ecofoodFacade.verifyProductsAddedFromHomePage();
        assertThat("SuccessfulAddProducts", productsAdded, equalTo(0));
    }

    @Test
    public void AddProductsFromCatalog() {

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.goToCatalogPage();
        ecofoodFacade.addCatalogProducts();
        int productsAdded = ecofoodFacade.verifyProductsAddedFromCatalogPage();
        assertThat("SuccessfulAddProducts", productsAdded, equalTo(0));

    }

    @Test
    public void AddProductAsUnauthenticatedUser() {

        ecofoodFacade.addProductsFromHomePage();
        int productsAdded = ecofoodFacade.getTotalProductsAdded();
        ecofoodFacade.goToLoginPage();
        ecofoodFacade.fillLoginForm();
        int productsAfterLogin = ecofoodFacade.getTotalProductsAdded();
        assertThat("SuccessfulAddProducts", productsAdded - productsAfterLogin, equalTo(0));
    }

    @Test
    public void AddAllStockProduct() {

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addAllStock();
        int result = ecofoodFacade.verifyAllStockAdded();
        assertThat("SuccessfulAddProducts", result, equalTo(0));
    }

    @Test
    public void decreaseTheQuantity() {

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        int before = ecofoodFacade.getTotalProductsAdded();
        ecofoodFacade.decreaseProductQuantity();
        int after = ecofoodFacade.getTotalProductsAdded();
        assertThat("SuccessfulDecreaseProducts", before - after, equalTo(1));
    }

    @Test
    public void emptyShoppingCart() {

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        ecofoodFacade.emptyCart();
        int result = ecofoodFacade.getTotalProductsAdded();
        assertThat("SuccessfulEmptyCart", result, equalTo(0));
    }

    @Test
    public void productDetailsAsALoggedUser() {

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.goToCatalogPage();
        ecofoodFacade.productDetails();
        boolean result = ecofoodFacade.isCorrectTheInformation();
        assertThat("Productdetails", result, equalTo(true));
    }
}
