package tests;

import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrdersFullfillmentTest extends Hooks {
    @Test
    public void verifyOrder() {
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
