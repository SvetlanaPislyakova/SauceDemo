package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class CartPage extends BasePage{

    private final By PRODUCT_IN_CART = By.xpath("//*[@data-test='inventory-item']");
    private final By PRODUCT_NAME_IN_CART = By.xpath("//*[@data-test='inventory-item-name']");
    private final By PRODUCT_PRICE_IN_CART = By.xpath("//*[@data-test='inventory-item-price']");
    private final By CONTINUE_SHOPPING_BTN = By.xpath("//*[@data-test='continue-shopping']");
    private final By CHECKOUT_BTN = By.xpath("//*[@data-test='checkout']");
    private final String REMOVE_PRODUCT_FROM_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='cart_item']/descendant::button[text()='Remove']";
    private final By TITLE = By.cssSelector("[data-test=title]");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CartPage open() {
        log.info("CartPage opening");
        driver.get(BASE_URL + "/cart.html");
        return this;
    }

    @Override
    public CartPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
    }

    public List<String> getProductNamesInCart() {
        List<WebElement> names = driver.findElements(PRODUCT_NAME_IN_CART);
        List<String> itemNames = new ArrayList<>();
        for (WebElement name : names) {
            itemNames.add(name.getText());
        }
        return itemNames;
    }

    public List<String> getProductPricesInCart() {
        List<WebElement> prices = driver.findElements(PRODUCT_PRICE_IN_CART);
        List<String> productPrices = new ArrayList<>();
        for(WebElement item : prices) {
            productPrices.add(item.getText());
        }
        return productPrices;
    }

    public List<Double> getProductPricesInCartAsDouble() {
        List<Double> productsPrices = new ArrayList<>();
        for (String price : getProductPricesInCart()) {
            productsPrices.add(Double.parseDouble(price.replace("$", "")));
        }
        return productsPrices;
    }

    public ProductsPage continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BTN).click();
        return new ProductsPage(driver);
    }

    public List<WebElement> getProductListInCart() {
        return driver.findElements(PRODUCT_IN_CART);
    }

    @Step("Удаление товара с названием '{productName}' из корзины")
    public CartPage removeProductFromCart(String productName) {
        log.info("Remove product {} from cart", productName);
        driver.findElement(By.xpath(String.format(REMOVE_PRODUCT_FROM_CART_PATTERN, productName))).click();
        return this;
    }

    public CheckoutPage checkout() {
        log.info("Go to CheckoutPage");
        driver.findElement(CHECKOUT_BTN).click();
        return new CheckoutPage(driver);
    }
}
