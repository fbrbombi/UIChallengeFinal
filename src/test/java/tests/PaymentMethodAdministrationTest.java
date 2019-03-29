package tests;

import helpers.EcofoodFacade;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class PaymentMethodAdministrationTest extends Hooks {


    @Test
    public void successfulCheckoutWithUnregisteredPayment(Method method) {

        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        ecofoodFacade.goToCheckoutPageFromHomePage();
        ecofoodFacade.checkoutWithoutPaymentMethod();
        ecofoodFacade.verifyCheckoutProcess();
        String result = ecofoodFacade.checkoutResponse();
        assertThat("SuccessfulAddProducts", result, equalTo("Compra Realizada"));

    }

    @Test
    public void successfulCheckoutWithRegisteredPayment(Method method) {

        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
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
