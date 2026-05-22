package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
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
    @Description("Проверка товара в корзине")
    @TmsLink("ITM-3")
    @Issue("ITM-3")
    public void checkProductInCart() {
        productsPage.addProductToCart("Sauce Labs Backpack")
                .goToCart();
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
    @Description("Проверка кнопки 'Продолжить покупки'")
    @TmsLink("ITM-4")
    @Issue("ITM-4")
    public void checkContinueShoppingBtn() {
        productsPage.addProductToCart("Sauce Labs Bike Light")
                .goToCart()
                .continueShopping();
        assertEquals(productsPage.getTitle(), "Products");
    }

    @Test(testName = "Проверка удаления товара из корзины",
            description = "Добавляет Bike Light в корзину, удаляет его и проверяет, что корзина пуста")
    @Description("Удаление товара из корзины")
    @TmsLink("ITM-5")
    @Issue("ITM-5")
    public void checkRemoveProductFromCart() {
        productsPage.addProductToCart("Sauce Labs Bike Light")
                .goToCart();
        softAssert.assertEquals(cartPage.getProductListInCart().size(), 1);
        cartPage.removeProductFromCart("Sauce Labs Bike Light");
        softAssert.assertTrue(cartPage.getProductListInCart().isEmpty());
        softAssert.assertAll();
    }

    @Test(testName = "Проверка кнопки 'Checkout'",
            description = "Добавляет товар в корзину, нажимает Checkout и проверяет переход на страницу " +
                    "'Checkout: Your Information'")
    @Description("Оформление заказа")
    @TmsLink("ITM-6")
    @Issue("ITM-6")
    public void checkoutOrder() {
        productsPage.addProductToCart("Sauce Labs Bike Light")
                .goToCart()
                .checkout();
        assertEquals(checkoutPage.getTitle(), "Checkout: Your Information");
    }
}
