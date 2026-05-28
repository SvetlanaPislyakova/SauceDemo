package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class LocatorsTest extends BaseTest {

    @Test
    public void checkLocators() {
        driver.get("https://www.saucedemo.com/");
        WebElement nameInput = driver.findElement(By.id("user-name"));
        nameInput.sendKeys(user);
        WebElement passwordInput = driver.findElement(By.name("password"));
        passwordInput.sendKeys(password);
        WebElement loginBtn = driver.findElement(By.className("submit-button"));
        loginBtn.click();

        WebElement button = driver.findElement(By.tagName("button"));
        WebElement linkText = driver.findElement(By.linkText("Sauce Labs Backpack"));
        WebElement partLinkText = driver.findElement(By.partialLinkText("Backpack"));
        WebElement backpackImg = driver.findElement(By.xpath("//img[@alt='Sauce Labs Backpack']"));
        WebElement backpackLink = driver.findElement(By.xpath("//div[text()='Sauce Labs Backpack']"));
        WebElement bikeLightImg = driver.findElement(By.xpath("//img[contains(@alt, 'Bike Light')]"));
        WebElement bikeLightLink = driver.findElement(By.xpath("//div[contains(text(), 'Bike Light')]"));
        WebElement jacketLink = driver.findElement(By.xpath("//div[text()='Sauce Labs Fleece Jacket']//ancestor::a"));
        WebElement onesieLink = driver.findElement(By.xpath("//a//descendant::div[contains(text(), 'Onesie')]"));
        WebElement bikeLightDescription = driver.findElement(By.xpath
                ("//div[@data-test='inventory-item-description']//following::div[contains(text(), 'A red light')]"));
        WebElement backpackPricebar = driver.findElement
                (By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']//parent::div"));
        WebElement backpackPrice = driver.findElement
                (By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']//preceding::div[1]"));
        WebElement tshirtLink = driver.findElement(By.xpath("//div[@data-test='inventory-item-name' and contains(text(), 'T-Shirt')]"));
        WebElement burgerMenu = driver.findElement(By.cssSelector(".bm-burger-button"));
        List<WebElement> addToCartBtn = driver.findElements(By.cssSelector(".btn.btn_primary"));
        WebElement cartIcon = driver.findElement(By.cssSelector(".shopping_cart_container .shopping_cart_link"));
        WebElement burgerMenu2 = driver.findElement(By.cssSelector("#react-burger-menu-btn"));
        List<WebElement> allImg = driver.findElements(By.cssSelector("img"));
        List<WebElement> allPricesLabel = driver.findElements(By.cssSelector("div.inventory_item_price"));
        WebElement jacketImg = driver.findElement(By.cssSelector("[data-test='inventory-item-sauce-labs-fleece-jacket-img']"));
        WebElement tshirtImg = driver.findElement(By.cssSelector("[alt~='T-Shirt']"));
        WebElement footer = driver.findElement(By.cssSelector("[data-test|='footer']"));
        WebElement filter = driver.findElement(By.cssSelector("[data-test^='product-sort']"));
        WebElement title = driver.findElement(By.cssSelector("[class$='logo']"));
        WebElement addToCartBackpackBtn = driver.findElement(By.cssSelector("[data-test*='cart-sauce-labs-backpack']"));
    }
}
