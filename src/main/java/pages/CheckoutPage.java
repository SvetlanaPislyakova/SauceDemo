package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

@Log4j2
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

    @Override
    public CheckoutPage open() {
        log.info("CheckoutPage opening");
        driver.get(BASE_URL + "/checkout-step-one.html");
        return this;
    }

    @Override
    public CheckoutPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    @Step("Заполнение формы значениями: '{firstName}', '{lastName}', '{code}'")
    public void fillInfo(String firstName, String lastName, String code) {
        log.info("Checkout with parameters: '{}', '{}', '{}'", firstName, lastName, code);
        driver.findElement(FIRST_NAME_INPUT).sendKeys(firstName);
        driver.findElement(LAST_NAME_INPUT).sendKeys(lastName);
        driver.findElement(CODE_INPUT).sendKeys(code);
        driver.findElement(CONTINUE_BTN).click();
    }

    public String getErrorMsg() {
        return driver.findElement(ERROR_MSG).getText();
    }
}
