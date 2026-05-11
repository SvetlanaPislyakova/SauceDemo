package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {

    @BeforeMethod
    public void login() {
        loginAsStandardUser();
        productsPage.addProductToCart("Sauce Labs Bike Light");
        productsPage.goToCart();
        cartPage.checkout();
    }

    @Test(testName = "Оформление заказа с корректными данными покупателя",
            description = "Проверяет, что при заполнении всех обязательных полей (имя, фамилия, код) " +
                    "осуществляется переход на страницу 'Checkout: Overview'")
    @Description("Оформление заказа - позитивный сценарий")
    @TmsLink("ITM-7")
    @Issue("ITM-7")
    public void checkoutWithAllFields() {
        checkoutPage.fillInfo("Svetlana", "Pislyakova", "123456");
        assertEquals(checkoutPage.getTitle(), "Checkout: Overview");
    }

    @DataProvider(name = "Тестовые данные для негативной валидации формы покупателя")
    public Object[][] negativeCheckoutData() {
        return new Object[][]{
                {"", "Pislyakova", "123456", "Error: First Name is required"},
                {"Svetlana", "", "123456", "Error: Last Name is required"},
                {"Svetlana", "Pislyakova", "", "Error: Postal Code is required"}
        };
    }

    @Test(testName = "Оформление заказа с неполными данными покупателя",
            description = "Проверяет, что при частичном заполнении данных о покупателе отображается ошибка",
            dataProvider = "Тестовые данные для негативной валидации формы покупателя")
    @Description("Оформление заказа - негативный сценарий")
    @TmsLink("ITM-8")
    @Issue("ITM-8")
    public void negativeCheckout(String firstName, String lastName, String postalCode, String errorMsg) {
        checkoutPage.fillInfo(firstName, lastName, postalCode);
        assertEquals(checkoutPage.getErrorMsg(), errorMsg);
    }
}
