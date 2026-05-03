package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class LoginTest extends BaseTest {

    @Test(testName = "Авторизация с корректными значениями логина и пароля",
            description = "Проверяет авторизацию с корректными значениями логина и пароля")
    public void checkLoginWithPositiveCred() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitle(), "Products");
    }

    @DataProvider(name = "Тестовые данные для негативного логина")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"test", "test", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "Тестовые данные для негативного логина",
            testName = "Авторизация с некорректными данными",
            description = "Проверяет сообщение об ошибке при авторизации с некорректными данными")
    public void negativeLogin(String username, String password, String errorMsg) {
        loginPage.open();
        loginPage.login(username, password);
        assertEquals(loginPage.getErrorMsg(), errorMsg);
    }
}
