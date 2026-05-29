package pages;

import io.qameta.allure.Step;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class ProductsPage extends BasePage {

    private final By TITLE = By.cssSelector("[data-test=title]");
    private final By PRODUCTS_LIST = By.xpath("//*[@data-test='inventory-item']");
    private final By FILTER = By.xpath("//*[@data-test='product-sort-container']");
    private final By PRODUCT_NAMES = By.xpath("//*[@data-test='inventory-item-name']");
    private final By PRODUCT_PRICES = By.xpath("//*[@data-test='inventory-item-price']");
    private final By CART_BADGE = By.xpath("//*[@data-test='shopping-cart-badge']");
    private final By GO_TO_CART_BTN = By.xpath("//*[@data-test='shopping-cart-link']");
    private final String ADD_TO_CART_PATTERN =
            "//*[text()='%s']/ancestor::div[@class='inventory_item']/descendant::button[text()='Add to cart']";

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ProductsPage open() {
        log.info("ProductsPage opening");
        driver.get(BASE_URL + "/inventory.html");
        return this;
    }

    @Override
    public ProductsPage isPageOpened() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(TITLE));
        return this;
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

    public ProductsPage chooseFilterByValue(String value) {
        Select filterOptions = new Select(driver.findElement(FILTER));
        filterOptions.selectByValue(value);
        return this;
    }

    public void chooseFilterByNameAToZ() {
        log.info("Choose filter by name from A to Z");
        chooseFilterByValue("az");
    }

    public void chooseFilterByNameZToA() {
        log.info("Choose filter by name from Z to A");
        chooseFilterByValue("za");
    }

    public void chooseFilterByPriceLowToHigh() {
        log.info("Choose filter by price from low to high");
        chooseFilterByValue("lohi");
    }

    public void chooseFilterByPriceHighToLow() {
        log.info("Choose filter by price from high to low");
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

    @Step("Добавление товара с названием '{productName}' в корзину")
    public ProductsPage addProductToCart(String productName) {
        log.info("Add product '{}' in cart", productName);
        driver.findElement(By.xpath(String.format(ADD_TO_CART_PATTERN, productName))).click();
        return this;
    }

    public int getCartBadgeCount() {
        return Integer.parseInt(driver.findElement(CART_BADGE).getText());
    }

    public CartPage goToCart() {
        log.info("Go to CartPage");
        driver.findElement(GO_TO_CART_BTN).click();
        return new CartPage(driver);
    }
}
