package tests;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PaymentMethodAdministrationTest extends Hooks {

    @Test
    public void successfulCheckoutWithUnregisteredPayment() {
        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        ecofoodFacade.goToCheckoutPageFromHomePage();
        ecofoodFacade.checkoutWithoutPaymentMethod();
        ecofoodFacade.verifyCheckoutProcess();
        String result = ecofoodFacade.checkoutResponse();
        assertThat("SuccessfulAddProducts", result, equalTo("Compra Realizada"));

    }

    @Test
    public void successfulCheckoutWithRegisteredPayment() {
        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.goToPaymentMethodPage();
        ecofoodFacade.addNewMethod();
        ecofoodFacade.goToHomePage();
        ecofoodFacade.addProductsFromHomePage();
        ecofoodFacade.goToCheckoutPageFromHomePage();
        ecofoodFacade.checkoutWithPaymentMethod();
        ecofoodFacade.verifyCheckoutProcess();
        String result = ecofoodFacade.checkoutResponse();
        assertThat("SuccessfulAddProducts", result, equalTo("Compra Realizada"));
    }

}
