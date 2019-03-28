package tests;

import helpers.EcofoodFacade;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrdersFullfillmentTest extends Hooks {


    EcofoodFacade ecofoodFacade;

    @Test
    public void verifyOrder() {


        System.out.println(Thread.currentThread().getId());
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
