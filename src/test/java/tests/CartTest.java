package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    @BeforeMethod
    public void login() {
        loginAsStandardUser();
    }

    @Test
    public void checkBackpackInCart() {
        productsPage.addBackpackToCart();
        productsPage.goToCart();
        List<String> itemNames = cartPage.getProductNamesInCart();
        List<Double> itemPrices = cartPage.getProductPricesInCartAsDouble();
        softAssert.assertEquals(itemNames.size(), 1);
        softAssert.assertEquals(itemPrices.size(), 1);
        softAssert.assertTrue(itemNames.contains("Sauce Labs Backpack"));
        softAssert.assertTrue(itemPrices.contains(29.99));
        softAssert.assertAll();
    }

    @Test
    public void checkContinueShoppingBtn() {
        productsPage.addBikeLightToCart();
        productsPage.goToCart();
        cartPage.continueShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test
    public void checkRemoveBikeLightFromCart() {
        productsPage.addBikeLightToCart();
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.getProductListInCart().size(), 1);
        cartPage.removeBikeLightFromCart();
        softAssert.assertTrue(cartPage.getProductListInCart().isEmpty());
        softAssert.assertAll();
    }

    @Test
    public void checkoutOrder() {
        productsPage.addBikeLightToCart();
        productsPage.goToCart();
        cartPage.checkout();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }
}
