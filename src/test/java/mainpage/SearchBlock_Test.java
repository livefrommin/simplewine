package mainpage;

import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.SearchBase;
import webdriver.BaseTest;

@Epic("Поиск")
//@Disabled
public class SearchBlock_Test extends BaseTest {

    SearchBase searchBase;

    @BeforeEach
    public void before() {
        searchBase = new SearchBase();
    }

    @Description("Поиск товара по Артикул")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1115")
    @Link(name = "Link to video", value = "checkSearchArticle", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"119607"})
    public void checkSearchArticle(String value) {
        searchBase.openSearchAndSetValue(value);
        searchBase.compareSearchArticle(value);
    }

    @Description("Поиск товара по названию")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1116")
    @Link(name = "Link to video", value = "checkSearchName", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Pinot Noir"})
    public void checkSearchName(String value) {
        searchBase.openSearchAndSetValue(value);
        searchBase.compareSearchEqInName(value);
    }

    @Description("Поиск товара по Стране")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1117")
    @Link(name = "Link to video", value = "checkSearchCountry", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Франция"})
    public void checkSearchCountry(String value) {
        searchBase.openSearchAndSetValue(value);
        searchBase.compareSearchEqInInfo(value);
    }

    @Description("Поиск товара по Году")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1118")
    @Link(name = "Link to video", value = "checkSearchYear", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"2009"})
    public void checkSearchYear(String value) {
        searchBase.openSearchAndSetValue(value);
        searchBase.compareSearchEqInName(value);
    }

    @Description("Поиск товара по цвету")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1119")
    @Link(name = "Link to video", value = "checkSearchColor", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Красное"})
    public void checkSearchColor(String value) {
        searchBase.openSearchAndSetValue(value);
        searchBase.compareSearchEqInInfo(value);
    }

    @Description("Поиск товара по содержанию сахара")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1120")
    @Link(name = "Link to video", value = "checkSearchSugar", type = "video")
    @ParameterizedTest(name = "{displayName}")
    @ValueSource(strings = {"Сухое"})
    public void checkSearchSugar(String value) {
        searchBase.openSearchAndSetValue(value);
        searchBase.compareSearchEqInInfo(value);
    }

}
