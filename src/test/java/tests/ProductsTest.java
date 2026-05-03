package tests;

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
    public void checkCountProductsOnPage() {
        assertEquals(productsPage.countProductsOnPage(), 6);
    }

    @Test(testName = "Проверка элементов фильтра",
            description = "Проверяет названия элементов фильтра")
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
    public void checkFilterByNameAToZ() {
        List<String> expectedNames = productsPage.getProductsNames();
        Collections.sort(expectedNames);
        productsPage.chooseFilterByNameAToZ();
        assertEquals(expectedNames, productsPage.getProductsNames());
    }

    @Test(testName = "Проверка сортировки по названию от Z до A",
            description = "Проверяет сортировку товаров по названию от Z до A")
    public void checkFilterByNameZToA() {
        List<String> expectedNames = productsPage.getProductsNames();
        Collections.sort(expectedNames);
        Collections.reverse(expectedNames);
        productsPage.chooseFilterByNameZToA();
        assertEquals(expectedNames, productsPage.getProductsNames());
    }

    @Test(testName = "Сортировка по возрастанию цены",
            description = "Проверяет сортировку по возрастанию цены")
    public void checkFilterByPriceLowToHigh() {
        List<Double> expectedPrices = productsPage.getProductsPricesAsDouble();
        Collections.sort(expectedPrices);
        productsPage.chooseFilterByPriceLowToHigh();
        assertEquals(expectedPrices, productsPage.getProductsPricesAsDouble());
    }

    @Test(testName = "Сортировка по убыванию цены",
            description = "Проверяет сортировку по убыванию цены")
    public void checkFilterByPriceHighToLow() {
        List<Double> expectedPrices = productsPage.getProductsPricesAsDouble();
        Collections.sort(expectedPrices);
        Collections.reverse(expectedPrices);
        productsPage.chooseFilterByPriceHighToLow();
        assertEquals(expectedPrices, productsPage.getProductsPricesAsDouble());
    }

    @Test(testName = "Добавление товара рюкзак в корзину",
            description = "Добавляет рюкзак в корзину и проверяет, что счетчик товаров в корзине равен 1")
    public void addBackPackToCart() {
        productsPage.addBackpackToCart();
        assertEquals(productsPage.getCartBadgeCount(), 1);
    }

    @Test(testName = "Добавление двух товаров в корзину",
            description = "Добавляет два товара в корзину и проверяет, что счетчик товаров в корзине равен 2")
    public void addTwoProductsToCart() {
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        assertEquals(productsPage.getCartBadgeCount(), 2);
    }
}
