package authorization;

import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import pages.RegisterPage;
import webdriver.BaseTest;

@Epic("Регистрация \" Авторизация")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AuthorizationBase_Test extends BaseTest {

    RegisterPage registerPage;

    @BeforeEach
    public void before() {
        registerPage = new RegisterPage();
    }

    @Description("Регистрация нового пользователя (1) ")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1122")
    @Link(name = "Link to video", value = "registrationUser", type = "video")
    @Test
    @Order(1)
    public void registrationNewUser() {
        mainPage.openUserDropAndClickRegistration();
        registerPage.setDataAndRegister(user).waitRegistrationCompletedAndCheck();
    }

    @Description("Вход через шапку сайта в существующий аккаунт (2)")
    @Severity(value = SeverityLevel.CRITICAL)
    @Issue("1059")
    @Link(name = "Link to video", value = "loginFromHeader", type = "video")
    @Test
    @Order(2)
    public void loginFromHeader() {
        mainPage.openUserDropAndLogin(user.getEmail(), user.getPassword())
                .checkUserIsLogin(user.getName());
    }

}
