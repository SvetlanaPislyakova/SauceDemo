package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
public class LoginPage extends BasePage{

    private final By USERNAME_INPUT = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_INPUT = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BTN = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MSG = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public LoginPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(LOGIN_BTN));
        return this;
    }

    @Step("Открытие страницы авторизации")
    @Override
    public LoginPage open() {
        log.info("LoginPage opening");
        driver.get(BASE_URL);
        return this;
    }

    @Step("Вход в магазин c именем пользователя: '{username}' и паролем: '{password}'")
    public ProductsPage login(String username, String password) {
        log.info("Log in with credential: user - '{}', password - '{}'", username, password);
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BTN).click();
        return new ProductsPage(driver);
    }

    @Step("Вход в магазин c именем пользователя: '{username}' и паролем: '{password}'")
    public LoginPage negativeLogin(String username, String password) {
        log.info("Negative log in with credential: user - '{}', password - '{}'", username, password);
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BTN).click();
        return this;
    }

    public String getErrorMsg() {
        return driver.findElement(ERROR_MSG).getText();
    }
}
