package pages.items;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class BannerBlock {

    final SelenideElement bannerBlock = $("div.main-slider > div.container > div.title-slider > div.swiper-wrapper");

    public BannerBlock() {
    }

    @Step("Проверка наличие баннера")
    public boolean checkBanner() {
        return bannerBlock.exists();
    }

    @Step("Проверка наличия кнопок навигации")
    public boolean checkNavigationButton() {
        return bannerBlock.find(".title-slider__navigation").exists();
    }
}
