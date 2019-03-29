package tests;

import helpers.EcofoodFacade;
import org.testng.annotations.Test;

import java.lang.reflect.Method;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrdersFullfillmentTest extends Hooks {


    @Test
    public void verifyOrder(Method method) {

        EcofoodFacade ecofoodFacade = setEcofoodFacade(method.getName());
        ecofoodFacade.userIsLoggedIn();
        ecofoodFacade.addProductsFromHomePage();
        ecofoodFacade.goToCheckoutPageFromHomePage();
        ecofoodFacade.checkoutWithoutPaymentMethod();
        ecofoodFacade.verifyCheckoutProcess();
        ecofoodFacade.goToOrderPage();
        ecofoodFacade.seeDetailLastOrder();
        boolean result = ecofoodFacade.verifyOrder();
        assertThat("SuccessfulAddProducts", result, equalTo(true));
    }

}
