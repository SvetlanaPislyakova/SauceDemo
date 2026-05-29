package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Авторизация с корректными значениями логина и пароля",
            description = "Проверяет авторизацию с корректными значениями логина и пароля")
    @Description("Авторизация с корректными значениями логина и пароля")
    @Epic("E2E")
    @Feature("Авторизация в SauceDemo")
    @Story("Авторизация - положительный сценарий")
    @Severity(SeverityLevel.CRITICAL)
    @Link("https://www.saucedemo.com/")
    @TmsLink("ITM-1")
    @Issue("ITM-1")
    @Flaky
    @Owner("Светлана Пислякова")
    public void checkLoginWithPositiveCred() {
        loginPage.open()
                 .isPageOpened()
                 .login(user, password);
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {user, "", "Epic sadface: Password is required"},
                {"", password, "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина",
            testName = "Авторизация с некорректными данными",
            description = "Проверяет сообщение об ошибке при авторизации с некорректными данными")
    @Description("Авторизация с некорректными значениями логина и пароля")
    @TmsLink("ITM-2")
    @Issue("ITM-2")
    public void negativeLogin(String username, String password, String errorMsg) {
        loginPage.open()
                .isPageOpened()
                .negativeLogin(username, password);
        assertEquals(loginPage.getErrorMsg(), errorMsg);
    }
}
