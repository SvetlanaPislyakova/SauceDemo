package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage{

    private final By PRODUCT_IN_CART = By.xpath("//*[@data-test='inventory-item']");
    private final By PRODUCT_NAME_IN_CART = By.xpath("//*[@data-test='inventory-item-name']");
    private final By PRODUCT_PRICE_IN_CART = By.xpath("//*[@data-test='inventory-item-price']");
    private final By CONTINUE_SHOPPING_BTN = By.xpath("//*[@data-test='continue-shopping']");
    private final By CHECKOUT_BTN = By.xpath("//*[@data-test='checkout']");
    private final String REMOVE_PRODUCT_FROM_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='cart_item']/descendant::button[text()='Remove']";
    public CartPage(WebDriver driver) {
        super(driver);
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

    public void continueShopping() {
        driver.findElement(CONTINUE_SHOPPING_BTN).click();
    }

    public List<WebElement> getProductListInCart() {
        return driver.findElements(PRODUCT_IN_CART);
    }

    @Step("Удаление товара с названием '{productName}' из корзины")
    public void removeProductFromCart(String productName) {
        driver.findElement(By.xpath(String.format(REMOVE_PRODUCT_FROM_CART_PATTERN, productName))).click();
    }

    public void checkout() {
        driver.findElement(CHECKOUT_BTN).click();
    }
}
