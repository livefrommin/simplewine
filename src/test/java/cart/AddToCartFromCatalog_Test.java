package cart;

import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import pages.BrandsPage;
import pages.CartPage;
import pages.CatalogPage;
import webdriver.BaseTest;

@Epic("Корзина")
@Feature("Добавление товара в корзину")
@Story("Добавление товара в корзину из каталога")
public class AddToCartFromCatalog_Test extends BaseTest {

    CatalogPage catalogPage;
    CartPage cartPage;
    BrandsPage brandsPage;

    @BeforeEach
    public void before() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        brandsPage = new BrandsPage();
    }

    @Description("Добавление товара в корзину из каталога (Вино)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("799")
    @Link(name = "Link to video", value = "addProductToCart_wine", type = "video")
    @Test
    public void addProductToCart_wine() {
        catalogPage
                .addRandomCart(Menu.WINE_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("Добавление товара в корзину из каталога (Крепкие) ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("800")
    @Link(name = "Link to video", value = "addProductToCart_ksn", type = "video")
    @Test
    public void addProductToCart_ksn() {
        catalogPage.addRandomCart(Menu.KSN_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("Добавление товара в корзину из каталога (Шампанское и игристое)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("801")
    @Link(name = "Link to video", value = "addProductToCart_champagne", type = "video")
    @Test
    public void addProductToCart_champagne() {
        catalogPage.addRandomCart(Menu.CHAMPAGNE_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("Добавление товара в корзину из каталога (Винные сеты)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("802")
    @Link(name = "Link to video", value = "addProductToCart_wineSet", type = "video")
    //broken test
    //@Test
    public void addProductToCart_wineSet() {
        catalogPage.addRandomCart(Menu.WINE_SET_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("Добавление товара в корзину из каталога (Вода и соки)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("803")
    @Link(name = "Link to video", value = "addProductToCart_water", type = "video")
    @Test
    public void addProductToCart_water() {
        catalogPage.addRandomCart(Menu.WATER_AND_JUICE_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("Добавление товара в корзину из каталога (Стекло)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("804")
    @Link(name = "Link to video", value = "addProductToCart_glass", type = "video")
    @Test
    public void addProductToCart_glass() {
        catalogPage.addRandomCart(Menu.GLASS_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("Добавление товара в корзину из каталога (Аксессуары) ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("805")
    @Link(name = "Link to video", value = "addProductToCart_accessory", type = "video")
    @Test
    public void addProductToCart_accessory() {
        catalogPage.addRandomCart(Menu.ACCESSORY_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("Добавление товара в корзину из каталога (Коллекция Simple)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("806")
    @Link(name = "Link to video", value = "addProductToCart_simpleCollection", type = "video")
    @Test
    public void addProductToCart_simpleCollection() {
        catalogPage.addRandomCart(Menu.SIMPLE_COLLECTION_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("")
    @Link(name = "Link to video", value = "addProductToCart_brands", type = "video")
    //@Test
    public void addProductToCart_brands() {
        mainPage.chooseMenuItem(Menu.BRANDS_NAME);
        brandsPage.chooseRandomBrands();
        catalogPage.addRandomCart(Menu.BRANDS_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("")
    @Link(name = "Link to video", value = "addProductToCart_stock", type = "video")
    //@Test
    public void addProductToCart_stock() {
        mainPage.chooseMenuItem(Menu.BRANDS_NAME);
        brandsPage.chooseRandomBrands();
        catalogPage.addRandomCart(Menu.BRANDS_NAME, 1)
                .openCart();
        cartPage.compareSelectedCarts();
    }

    @Description("")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("")
    @Link(name = "Link to video", value = "addMultiplyProductToCart", type = "video")
    @Test
    @Disabled
    public void addMultiplyProductToCart() {
        catalogPage.addRandomCart(Menu.WINE_NAME, 3).openCart();
        cartPage.compareSelectedCarts();
    }
}
