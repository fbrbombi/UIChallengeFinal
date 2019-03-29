package tests;

import helpers.EcofoodFacade;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ShoppingProcessTest extends Hooks {

    @Test
    public void AddProductsFromHomePage(Method method) {

        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        int productsAdded = ecofoodFacade.verifyProductsAddedFromHomePage();
        assertThat("SuccessfulAddProducts", productsAdded, equalTo(0));
    }

    @Test
    public void AddProductsFromCatalog(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.goToCatalogPage();
        ecofoodFacade.addCatalogProducts();
        int productsAdded = ecofoodFacade.verifyProductsAddedFromCatalogPage();
        assertThat("SuccessfulAddProducts", productsAdded, equalTo(0));

    }

    @Test
    public void AddProductAsUnauthenticatedUser(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());

        ecofoodFacade.addProductsFromHomePage();
        int productsAdded = ecofoodFacade.getTotalProductsAdded();
        ecofoodFacade.goToLoginPage();
        ecofoodFacade.fillLoginForm();
        int productsAfterLogin = ecofoodFacade.getTotalProductsAdded();
        assertThat("SuccessfulAddProducts", productsAdded - productsAfterLogin, equalTo(0));
    }

    @Test
    public void AddAllStockProduct(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addAllStock();
        int result = ecofoodFacade.verifyAllStockAdded();
        assertThat("SuccessfulAddProducts", result, equalTo(0));
    }

    @Test
    public void decreaseTheQuantity(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        int before = ecofoodFacade.getTotalProductsAdded();
        ecofoodFacade.decreaseProductQuantity();
        int after = ecofoodFacade.getTotalProductsAdded();
        assertThat("SuccessfulDecreaseProducts", before - after, equalTo(1));
    }

    @Test
    public void emptyShoppingCart(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        ecofoodFacade.emptyCart();
        int result = ecofoodFacade.getTotalProductsAdded();
        assertThat("SuccessfulEmptyCart", result, equalTo(0));
    }

    @Test
    public void productDetailsAsALoggedUser(Method method) {
        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());

        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.goToCatalogPage();
        ecofoodFacade.productDetails();
        boolean result = ecofoodFacade.isCorrectTheInformation();
        assertThat("Productdetails", result, equalTo(true));
    }
}
