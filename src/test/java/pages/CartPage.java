package pages;

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
    private final By REMOVE_BIKE_LIGHT_FROM_CART = By.xpath("//*[@data-test='remove-sauce-labs-bike-light']");
    private final By CHECKOUT_BTN = By.xpath("//*[@data-test='checkout']");

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
        List<WebElement> products = driver.findElements(PRODUCT_PRICE_IN_CART);
        List<String> productNames = new ArrayList<>();
        for(WebElement item : products) {
            productNames.add(item.getText());
        }
        return productNames;
    }

    public List<Double> getProductPricesInCartAsDouble() {
        List<Double> productsPrices = new ArrayList<>();
        System.out.println(getProductPricesInCart());
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

    public void removeBikeLightFromCart() {
        driver.findElement(REMOVE_BIKE_LIGHT_FROM_CART).click();
    }

    public void checkout() {
        driver.findElement(CHECKOUT_BTN).click();
    }
}
