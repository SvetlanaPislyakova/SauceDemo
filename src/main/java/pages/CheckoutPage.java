package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BasePage{

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By FIRST_NAME_INPUT = By.xpath("//*[@data-test='firstName']");
    private final By LAST_NAME_INPUT = By.xpath("//*[@data-test='lastName']");
    private final By CODE_INPUT = By.xpath("//*[@data-test='postalCode']");
    private final By CONTINUE_BTN = By.xpath("//*[@data-test='continue']");
    private final By ERROR_MSG = By.xpath("//*[@data-test='error']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    @Step("Заполнение формы значениями: '{firstName}', '{lastName}', '{code}'")
    public void fillInfo(String firstName, String lastName, String code) {
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(CODE_INPUT).sendKeys(code);
        driver.findElement(CONTINUE_BTN).click();
    }

    public String getErrorMsg() {
        return driver.findElement(ERROR_MSG).getText();
    }
}
