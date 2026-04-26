package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void login() {
        loginAsStandardUser();
        productsPage.addBikeLightToCart();
        productsPage.goToCart();
        cartPage.checkout();
    }

    @Test
    public void completeCheckout() {
        checkoutPage.fillInfo("Svetlana", "Pislyakova", "123456");
        assertEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @Test
    public void checkoutWithEptyFirstName() {
        checkoutPage.fillInfo("", "Pislyakova", "123456");
        assertEquals(checkoutPage.getErrorMsg(), "Error: First Name is required");
    }

    @Test
    public void checkoutWithEptyLastName() {
        checkoutPage.fillInfo("Svetlana", "", "123456");
        assertEquals(checkoutPage.getErrorMsg(), "Error: Last Name is required");
    }

    @Test
    public void checkoutWithEptyCode() {
        checkoutPage.fillInfo("Svetlana", "Pislyakova", "");
        assertEquals(checkoutPage.getErrorMsg(), "Error: Postal Code is required");
    }
}
