package tests;

import io.qameta.allure.Description;
import io.qameta.allure.Issue;
import io.qameta.allure.TmsLink;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    private final SoftAssert softAssert = new SoftAssert();

    @BeforeMethod
    public void login() {
        loginAsStandardUser();
    }

    @Test(testName = "Проверка количества товаров на странице",
            description = "Проверяет количество отображаемых товаров на странице")
    @Description("Проверка количества отображаемых элементов")
    @TmsLink("ITM-9")
    @Issue("ITM-9")
    public void checkCountProductsOnPage() {
        assertEquals(productsPage.countProductsOnPage(), 6);
    }

    @Test(testName = "Проверка элементов фильтра",
            description = "Проверяет названия элементов фильтра")
    @Description("Проверка элементов фильтра")
    @TmsLink("ITM-10")
    @Issue("ITM-10")
    public void checkFilterItems() {
        Set<String> expectedItems = Set.of("Name (A to Z)", "Name (Z to A)", "Price (low to high)",
                "Price (high to low)");
        List<String> filterItems = productsPage.getFilterItems();
        softAssert.assertEquals(filterItems.size(), expectedItems.size());
        for (String item : filterItems) {
            softAssert.assertTrue(expectedItems.contains(item));
        }
        softAssert.assertAll();
    }

    @Test(testName = "Проверка сортировки по названию от A до Z",
            description = "Проверяет сортировку товаров по названию от A до Z")
    @Description("Сортировка по названию от A до Z")
    @TmsLink("ITM-11")
    @Issue("ITM-11")
    public void checkFilterByNameAToZ() {
        List<String> expectedNames = productsPage.getProductsNames();
        Collections.sort(expectedNames);
        productsPage.chooseFilterByNameAToZ();
        assertEquals(expectedNames, productsPage.getProductsNames());
    }

    @Test(testName = "Проверка сортировки по названию от Z до A",
            description = "Проверяет сортировку товаров по названию от Z до A")
    @Description("Сортировка по названию от Z до A")
    @TmsLink("ITM-12")
    @Issue("ITM-12")
    public void checkFilterByNameZToA() {
        List<String> expectedNames = productsPage.getProductsNames();
        Collections.sort(expectedNames);
        Collections.reverse(expectedNames);
        productsPage.chooseFilterByNameZToA();
        assertEquals(expectedNames, productsPage.getProductsNames());
    }

    @Test(testName = "Сортировка по возрастанию цены",
            description = "Проверяет сортировку по возрастанию цены")
    @Description("Сортировка по возрастанию цены")
    @TmsLink("ITM-13")
    @Issue("ITM-13")
    public void checkFilterByPriceLowToHigh() {
        List<Double> expectedPrices = productsPage.getProductsPricesAsDouble();
        Collections.sort(expectedPrices);
        productsPage.chooseFilterByPriceLowToHigh();
        assertEquals(expectedPrices, productsPage.getProductsPricesAsDouble());
    }

    @Test(testName = "Сортировка по убыванию цены",
            description = "Проверяет сортировку по убыванию цены")
    @Description("Сортировка по убыванию цены")
    @TmsLink("ITM-14")
    @Issue("ITM-14")
    public void checkFilterByPriceHighToLow() {
        List<Double> expectedPrices = productsPage.getProductsPricesAsDouble();
        Collections.sort(expectedPrices);
        Collections.reverse(expectedPrices);
        productsPage.chooseFilterByPriceHighToLow();
        assertEquals(expectedPrices, productsPage.getProductsPricesAsDouble());
    }

    @Test(testName = "Добавление товара рюкзак в корзину",
            description = "Добавляет рюкзак в корзину и проверяет, что счетчик товаров в корзине равен 1")
    @Description("Добавление товара в корзину")
    @TmsLink("ITM-15")
    @Issue("ITM-15")
    public void addProductToCart() {
        productsPage.addProductToCart("Sauce Labs Backpack");
        assertEquals(productsPage.getCartBadgeCount(), 1);
    }

    @Test(testName = "Добавление двух товаров в корзину",
            description = "Добавляет два товара в корзину и проверяет, что счетчик товаров в корзине равен 2")
    @Description("Добавление 2-х товаров в корзину, проверка счетчика")
    @TmsLink("ITM-16")
    @Issue("ITM-16")
    public void addTwoProductsToCart() {
        productsPage.addProductToCart("Sauce Labs Backpack")
                .addProductToCart("Sauce Labs Bike Light");
        assertEquals(productsPage.getCartBadgeCount(), 2);
    }
}
