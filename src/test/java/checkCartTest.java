import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.time.Duration;
import java.util.HashMap;

public class checkCartTest {

    @Test
    public void checkCart() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("credentials_enable_service", false);
        chromePrefs.put("profile.password_manager_enabled", false);
        options.setExperimentalOption("prefs", chromePrefs);
        options.addArguments("--incognito");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        WebDriver driver = new ChromeDriver(options);

        SoftAssert softAssert = new SoftAssert();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com/");

        WebElement nameInput = driver.findElement(By.xpath("//*[@data-test='username']"));
        nameInput.sendKeys("standard_user");

        WebElement passwordInput = driver.findElement(By.xpath("//*[@data-test='password']"));
        passwordInput.sendKeys("secret_sauce");

        WebElement loginBtn = driver.findElement(By.xpath("//*[@data-test='login-button']"));
        loginBtn.click();

        WebElement addBackPackToCart = driver.findElement(By.xpath("//*[@data-test='add-to-cart-sauce-labs-backpack']"));
        String priceBackpack = driver.findElement(By.xpath("//button[@data-test='add-to-cart-sauce-labs-backpack']//preceding-sibling::div")).getText();

        addBackPackToCart.click();

        WebElement goToCart = driver.findElement(By.xpath("//*[@data-test='shopping-cart-link']"));
        goToCart.click();

        String nameInCart = driver.findElement(By.xpath("//*[@data-test='inventory-item-name']")).getText();
        String priceInCart = driver.findElement(By.xpath("//*[@data-test='inventory-item-price']")).getText();

        softAssert.assertEquals(nameInCart, "Sauce Labs Backpack");
        softAssert.assertEquals(priceInCart, priceBackpack);

        driver.quit();
    }
}
