package pages;

import com.codeborne.selenide.Condition;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pages.modal.LoginAndRegisterModal;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainPage {

    final static Logger logger = LogManager.getLogger(FiltersBase.class);

    final String pushTipButton = ".PushTip-button";
    final String userDrop = "div.page-header__profile-wrapper.page-header__hide-on-cart > div > button";
    final String userDropTitle = ".userdrop__title";

    public MainPage() {
    }

    @Step("Открываем главную страницу, и подтверждаем возраст")
    public MainPage openAndConfirmAge() {
        $("div.age-confirm__button > button")
                .shouldHave(text("Мне исполнилось 18 лет")).click();
        return this;
    }

    @Step("Выбрать пункт меню {itemName}")
    public MainPage chooseMenuItem(String itemName) {
        $$("nav > div.navigation__row > ul > li > a").find(text(itemName)).click();
        return this;
    }

    @Step("Навести на пункт меню {itemName}")
    public MainPage hoverMenuItem(String itemName) {
        logger.info("Hover on menu item " + itemName);
        $$("nav > div.navigation__row > ul > li > a").find(text(itemName)).hover();
        return this;
    }

    @Step("Навести на пункт меню {itemName}")
    public MainPage hoverMenuItemLink(String itemName) {
        logger.info("Hover on menu item " + itemName);
        $$("nav > div.navigation__row > ul > li > a")
                .stream().filter(e -> e.getAttribute("href").contains(itemName))
                .findFirst().get().hover();
        return this;
    }

    @Step("Выбираем подпункт меню {value}")
    public void chooseSubMenuItem(String value) {
        logger.info("Choose sub menu item " + value);
        $$(".drop-filters__list-item > .drop-filters__link")
                .filter(Condition.visible)
                .find(Condition.textCaseSensitive(value)).click();
    }

    @Step("Выбрать элемент шапки {itemName}")
    public MainPage openHeaderElement(String itemName) {
        $$(".page-header .page-header__container > div")
                .find(text(itemName)).click();
        return this;
    }

    @Step("Проверяем что пользователь залогинен")
    public void checkUserIsLogin(String userName) {
        $(userDrop).click();
        boolean result = $(userDrop)
                .shouldBe(visible, enabled)
                .getText().equals("С возвращением, " + userName);
        assertTrue(result, "В шапке всплывающего окна пользовател, отображается некорректный текст.");
    }

    @Step("Логинимся под пользователем {email} - {password}")
    public MainPage openUserDropAndLogin(String email, String password) {
        $(userDrop).click();
        LoginAndRegisterModal modal = new LoginAndRegisterModal();
        modal.getLoginByEmail().click();
        modal.getLoginEmail().setValue(email);
        modal.getLoginPassword().setValue(password);
        modal.getLoginEmail().click();
        return this;
    }

    @Step("Попадаем на страницу регистрации через шапку сайта")
    public MainPage openUserDropAndClickRegistration() {
        $(userDrop).click();
        LoginAndRegisterModal modal = new LoginAndRegisterModal();
        modal.getRegisterButton().click();
        return this;
    }

    @Step("Открываем корзину")
    public void openCart() {
        open("personal/cart/");
    }

    @Step("Закрыть пуштип")
    public MainPage closePushTip() {
        $(pushTipButton).should(exist).click();
        return this;
    }
}
