package mainpage;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeAll;
import pages.MainPage;
import pages.items.BannerBlock;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BannersBlock_Test {

    final BannerBlock bannerBlock = new BannerBlock();

    @BeforeAll
    public static void before() {
        MainPage mainPage = new MainPage();
        mainPage.openAndConfirmAge();
    }

    @Epic("Главная страница")
    @Feature("Блок баннеров")
    @Story("Наличие элементов")
    @Description("Блок баннеров присутствует на главной странице")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("none")
    //@Test
    public void checkBannerBlockExist() {
        boolean exist = bannerBlock.checkBanner();
        assertTrue(exist);
    }

    @Epic("Главная страница")
    @Feature("Блок баннеров")
    @Story("Наличие элементов")
    @Description("Наличие кнопок навигации")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("none")
    //@Test
    public void checkNavigationButtonExist() {
        boolean exist = bannerBlock.checkNavigationButton();
        assertTrue(exist);
    }
}
