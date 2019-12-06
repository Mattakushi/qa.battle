import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;

@ExtendWith(BaseClass.class)
class LoginTest extends BaseTestClass {

    @Test
    @DisplayName(value = "Login")
    void login() {
        loginPage.open()
                .enterLogin("test")
                .enterPassword("test")
                .login()
                .acceptAlert();
        loginPage.acceptAlert();
        loginPage.positiveCheck();

        loginPage.logout();
    }

    @Test
    @DisplayName(value = "Negative login with accept first alert")
    void negativeLoginWithAcceptFirstAlert() {
        loginPage.open()
                .enterLogin("null")
                .enterPassword("null")
                .login()
                .acceptAlert();
        loginPage.dismissAlert();
        loginPage.negativeCheck();

        loginPage.logout();
    }

    @Test
    @DisplayName(value = "Negative login with accept two alert")
    void negativeLoginWithAcceptTwoAlert() {
        loginPage.open()
                .enterLogin("null")
                .enterPassword("null")
                .login()
                .acceptAlert();
        loginPage.acceptAlert();
        loginPage.negativeCheck();

        loginPage.logout();
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName(value = "Negative login with null and empty source")
    void negativeLogin(String emtyAndNull) {
        loginPage.open()
                .enterLogin(emtyAndNull)
                .enterPassword(emtyAndNull)
                .login()
                .acceptAlert();
        loginPage.acceptAlert();
        loginPage.negativeCheck();

        loginPage.logout();
    }

    @Test
    @DisplayName(value = "Login with cookie")
    void cookieLogin() {
        loginPage.open()
                .cookieLogin();
        loginPage.logout();
    }
}
