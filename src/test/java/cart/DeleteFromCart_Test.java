package cart;

import constants.Menu;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pages.BrandsPage;
import pages.CartPage;
import pages.CatalogPage;
import webdriver.BaseTest;

@Epic("Корзина")
@Feature("Удаление товара из корзины")
public class DeleteFromCart_Test extends BaseTest {

    CatalogPage catalogPage;
    CartPage cartPage;
    BrandsPage brandsPage;

    @BeforeEach
    public void before() {
        mainPage.chooseMenuItem(Menu.CHAMPAGNE_NAME);
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
        brandsPage = new BrandsPage();
    }

    @Description("Удаление товара из корзины ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("996")
    @Link(name = "Link to video", value = "deleteFromCart", type = "video")
    @Test
    public void deleteFromCart() {
        catalogPage
                .addRandomCart(Menu.WINE_NAME, 2)
                .openCart();
        cartPage.deleteAllProductViaButton();
    }

    @Description("Удаление товара из корзины (из header)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("997")
    @Link(name = "Link to video", value = "deleteFromPopup", type = "video")
    @Test
    public void deleteFromPopup() {
        catalogPage
                .addRandomCart(Menu.WINE_NAME, 2);
        cartPage.deleteAllViaButtonClearAll();
    }
}
