package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

    private final By USERNAME_INPUT = By.xpath("//*[@data-test='username']");
    private final By PASSWORD_INPUT = By.xpath("//*[@data-test='password']");
    private final By LOGIN_BTN = By.xpath("//*[@data-test='login-button']");
    private final By ERROR_MSG = By.xpath("//*[@data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String username, String password) {
        driver.findElement(USERNAME_INPUT).sendKeys(username);
        driver.findElement(PASSWORD_INPUT).sendKeys(password);
        driver.findElement(LOGIN_BTN).click();
    }

    public String getErrorMsg() {
        return driver.findElement(ERROR_MSG).getText();
    }
}
