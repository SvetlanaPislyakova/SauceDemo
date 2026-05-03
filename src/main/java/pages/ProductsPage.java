package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By PRODUCTS_LIST = By.xpath("//*[@data-test='inventory-item']");
    private final By FILTER = By.xpath("//*[@data-test='product-sort-container']");
    private final By PRODUCT_NAMES = By.xpath("//*[@data-test='inventory-item-name']");
    private final By PRODUCT_PRICES = By.xpath("//*[@data-test='inventory-item-price']");
    private final By ADD_BACKPACK_TO_CART_BTN =
            By.xpath("//*[@data-test='add-to-cart-sauce-labs-backpack']");
    private final By REMOVE_BACKPACK_FROM_CART_BTN =
            By.xpath("//*[@data-test='remove-sauce-labs-backpack']");
    private final By ADD_BIKE_LIGHT_TO_CART_BTN =
            By.xpath("//*[@data-test='add-to-cart-sauce-labs-bike-light']");
    private final By REMOVE_BIKE_LIGHT_FROM_CART_BTN =
            By.xpath("//*[@data-test='remove-sauce-labs-bike-light']");
    private final By CART_BADGE = By.xpath("//*[@data-test='shopping-cart-badge']");
    private final By GO_TO_CART_BTN = By.xpath("//*[@data-test='shopping-cart-link']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void open(){
        driver.get(BASE_URL + "/inventory.html");
    }

    public String getTitle() {
        return driver.findElement(TITLE).getText();
    }

    public int countProductsOnPage() {
        return driver.findElements(PRODUCTS_LIST).size();
    }

    public List<String> getFilterItems() {
        Select filterOptions = new Select(driver.findElement(FILTER));
        List<String> options = new ArrayList<>();
        for(WebElement option : filterOptions.getOptions()) {
            options.add(option.getText());
        }
        return options;
    }

    public void chooseFilterByValue(String value) {
        Select filterOptions = new Select(driver.findElement(FILTER));
        filterOptions.selectByValue(value);
    }

    public void chooseFilterByNameAToZ() {
        chooseFilterByValue("az");
    }

    public void chooseFilterByNameZToA() {
        chooseFilterByValue("za");
    }

    public void chooseFilterByPriceLowToHigh() {
        chooseFilterByValue("lohi");
    }

    public void chooseFilterByPriceHighToLow() {
        chooseFilterByValue("hilo");
    }

    public List<String> getProductsNames() {
        List<WebElement> products = driver.findElements(PRODUCT_NAMES);
        List<String> productNames = new ArrayList<>();
        for(WebElement item : products) {
            productNames.add(item.getText());
        }
        return productNames;
    }

    public List<String> getProductsPrices() {
        List<WebElement> products = driver.findElements(PRODUCT_PRICES);
        List<String> productNames = new ArrayList<>();
        for(WebElement item : products) {
            productNames.add(item.getText());
        }
        return productNames;
    }

    public List<Double> getProductsPricesAsDouble() {
        List<Double> productsPrices = new ArrayList<>();
        for (String price : getProductsPrices()) {
            productsPrices.add(Double.parseDouble(price.replace("$", "")));
        }
        return productsPrices;
    }

    public void addBackpackToCart() {
        driver.findElement(ADD_BACKPACK_TO_CART_BTN).click();
        driver.findElement(REMOVE_BACKPACK_FROM_CART_BTN).isDisplayed();
    }

    public void addBikeLightToCart() {
        driver.findElement(ADD_BIKE_LIGHT_TO_CART_BTN).click();
        driver.findElement(REMOVE_BIKE_LIGHT_FROM_CART_BTN).isDisplayed();
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(driver.findElement(CART_BADGE).getText());
    }

    public void goToCart() {
        driver.findElement(GO_TO_CART_BTN).click();
    }
}
