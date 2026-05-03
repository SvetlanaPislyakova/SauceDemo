package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class CartTest extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void login() {
        loginAsStandardUser();
    }

    @Test(testName = "Проверка наличия рюкзака в корзине",
            description = "Добавляет Sauce Labs Backpack в корзину, переходит в корзину и проверяет количество товаров," +
                    "название и цену")
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

    @Test(testName = "Проверка кнопки 'Continue shopping'",
            description = "Нажимает кнопку 'Continue shopping' в корзине и проверяет переход на страницу 'Products'")
    public void checkContinueShoppingBtn() {
        productsPage.addBikeLightToCart();
        productsPage.goToCart();
        cartPage.continueShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка удаления товара из корзины",
            description = "Добавляет Bike Light в корзину, удаляет его и проверяет, что корзина пуста")
    public void checkRemoveBikeLightFromCart() {
        productsPage.addBikeLightToCart();
        productsPage.goToCart();
        softAssert.assertEquals(cartPage.getProductListInCart().size(), 1);
        cartPage.removeBikeLightFromCart();
        softAssert.assertTrue(cartPage.getProductListInCart().isEmpty());
        softAssert.assertAll();
    }

    @Test(testName = "Проверка кнопки 'Checkout'",
            description = "Добавляет товар в корзину, нажимает Checkout и проверяет переход на страницу " +
                    "Checkout: Your Information'")
    public void checkoutOrder() {
        productsPage.addBikeLightToCart();
        productsPage.goToCart();
        cartPage.checkout();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }
}
