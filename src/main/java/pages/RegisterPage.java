package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.items.User;

import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static helpers.SelenideHelper.click;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterPage {

    SelenideElement inputFieldName = $(byName("name"));
    SelenideElement inputFieldEmail = $$(byName("email")).filter(Condition.cssClass("ui-input__field")).find(Condition.visible);
    SelenideElement inputFieldPhone = $$(byName("phone")).filter(Condition.cssClass("ui-input__field")).find(Condition.visible);
    SelenideElement inputFiledPassword = $(byName("password"));

    SelenideElement checkBoxMakeLoyalty = $(byName("makeLoyalty"));
    SelenideElement checkBoxInviteClub = $(byName("club"));
    SelenideElement checkBoxUserTerms = $(byName("terms-accept"));

    SelenideElement registrationButton = $("div.login__reg > form > div > button");

    public RegisterPage() {
    }

    @Step("Устанавливаем клиентские данные для регистрации")
    public RegisterPage setDataAndRegister(User user) {
        inputFieldName.setValue(user.getName());
        inputFieldEmail.setValue(user.getEmail());
        inputFieldPhone.setValue(user.getPhoneNumber());
        inputFiledPassword.setValue(user.getPassword());
        setTermsAccept();
        click(registrationButton);
        return this;
    }

    @Step("Ставим чекбокс на соглашение с условиями")
    public void setTermsAccept() {
        if (checkBoxUserTerms.isSelected()) return;
        checkBoxUserTerms.parent().click();
    }

    @Step("Проверяем успешность регистрации.")
    public RegisterPage waitRegistrationCompletedAndCheck() {
        boolean result = $(".login__report-text.js-report-text")
                .shouldBe(Condition.visible, Condition.enabled)
                .getText()
                .equals("Регистрация прошла успешно");
        assertTrue(result, "Не удалось дождаться регистрации, или не корректный текст.");
        return this;
    }
}
