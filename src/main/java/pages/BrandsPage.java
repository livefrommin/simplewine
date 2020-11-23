package pages;

import com.codeborne.selenide.SelenideElement;
import helpers.CollectionHelper;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$$;

public class BrandsPage {

    final String allBrandsPath = "#tabs-content > div.brands__list brands__list_top > div.brands__item";

    @Step("Выбираем произвольный бренд")
    public BrandsPage chooseRandomBrands() {
        SelenideElement brand = CollectionHelper.getRandom($$(allBrandsPath));
        brand.click();
        return this;
    }

}
