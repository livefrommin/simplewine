package cart;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.CartPage;
import pages.CatalogPage;
import webdriver.BaseTest;

@Epic("Корзина")
@Feature("Добавление товара в корзину")
@Story("Добавление товара в корзину из карточки товара ")
public class AddToCartFromProduct_Test extends BaseTest {

    CatalogPage catalogPage;
    CartPage cartPage;

    @BeforeEach
    public void before() {
        catalogPage = new CatalogPage();
        cartPage = new CartPage();
    }

    @Description("Добавление товара в корзину из карточки товара (Вино)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issues(value = {
            @Issue("985"),
            @Issue("986"),
            @Issue("987"),
            @Issue("988"),
            @Issue("989"),
            @Issue("990"),
            @Issue("991"),
            @Issue("992")
    })
    @Link(name = "Link to video", value = "addProductToCartFromCard_wine", type = "video")
    @ParameterizedTest(name = "{displayName}-{0}")
    @ValueSource(strings = {"Вино", "Шампанское и игристое", "Крепкие напитки", "Вода и соки", "Бокалы", "Аксессуары", "Simple collection"})
    public void addProductToCartFromCard_wine(String menuItem) {
        mainPage.chooseMenuItem(menuItem);
        catalogPage.addToCartAndCompareResult(cartPage);
    }
}
