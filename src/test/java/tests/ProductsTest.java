package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class ProductsTest extends BaseTest {

    @BeforeMethod
    public void login() {
        loginAsStandardUser();
    }

    @Test
    public void checkCountProductsOnPage() {
        assertEquals(productsPage.countProductsOnPage(), 6);
    }

    @Test
    public void checkFilterItems(){
        Set<String> expectedItems = Set.of("Name (A to Z)", "Name (Z to A)", "Price (low to high)",
                "Price (high to low)");
        List<String> filterItems = productsPage.getFilterItems();
        softAssert.assertEquals(filterItems.size(), expectedItems.size());
        for(String item : filterItems) {
            softAssert.assertTrue(expectedItems.contains(item));
        }
        softAssert.assertAll();
    }

    @Test
    public void checkFilterByNameAToZ() {
        List<String> expectedNames = productsPage.getProductsNames();
        Collections.sort(expectedNames);
        productsPage.chooseFilterByNameAToZ();
        assertEquals(expectedNames, productsPage.getProductsNames());
    }

    @Test
    public void checkFilterByNameZToA() {
        List<String> expectedNames = productsPage.getProductsNames();
        Collections.sort(expectedNames);
        Collections.reverse(expectedNames);
        productsPage.chooseFilterByNameZToA();
        assertEquals(expectedNames, productsPage.getProductsNames());
    }

    @Test
    public void checkFilterByPriceLowToHigh() {
        List<Double> expectedPrices = productsPage.getProductsPricesAsDouble();
        Collections.sort(expectedPrices);
        productsPage.chooseFilterByPriceLowToHigh();
        assertEquals(expectedPrices, productsPage.getProductsPricesAsDouble());
    }

    @Test
    public void checkFilterByPriceHighToLow() {
        List<Double> expectedPrices = productsPage.getProductsPricesAsDouble();
        Collections.sort(expectedPrices);
        Collections.reverse(expectedPrices);
        productsPage.chooseFilterByPriceHighToLow();
        assertEquals(expectedPrices, productsPage.getProductsPricesAsDouble());
    }

    @Test
    public void addBackPackToCart() {
        productsPage.addBackpackToCart();
        assertEquals(productsPage.getCartBadgeCount(), 1);
    }

    @Test
    public void addTwoProductsToCart() {
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        assertEquals(productsPage.getCartBadgeCount(), 2);
    }
}
