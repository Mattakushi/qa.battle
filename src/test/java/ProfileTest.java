import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@ExtendWith(BaseClass.class)
class ProfileTest extends BaseTestClass {

    @BeforeEach
    void loginAndOpen() {
        loginPage.open()
                .cookieLogin();
        profilePage.open();

    }

    @ParameterizedTest
    @CsvSource(value = {"Awesome AQA First Name:Awesome AQA Last Name", "null:null", "\n:\n"}, delimiter = ':')
    @DisplayName(value = "Check user info")
    void checkUserInfo(String firstName, String lastName) {
        profilePage.fillFirstName(firstName)
                .fillLastName(lastName)
                .saveUserInfo()
                .refreshPage();
        profilePage
                .checkFirstName(firstName)
                .checkLastName(lastName);
    }

    @ParameterizedTest
    @CsvSource(value = {"0000 0000 0000 0000:1:15", "AAAA aaaa AAAA aaaa:3:11", "!@#$ %^&* )(*& ^%$#:2:1"}, delimiter = ':')
    @DisplayName(value = "Check payment info")
    void checkPaymentInfo(String cardNumber, String paymentSystem, String dayOfPayment) {
        profilePage.openPaymentInfo()
                .fillCardNumber(cardNumber)
                .selectPaymentSystem(paymentSystem)
                .selectDayOfPayment(dayOfPayment)
                .savePaymentInfo()
                .refreshPage();
        profilePage.openPaymentInfo()
                .checkCardNumber(cardNumber)
                .checkPaymentSystem(paymentSystem)
                .checkDayOfPayment(dayOfPayment);

    }

    @AfterEach
    void logout() {
        loginPage.logout();
    }

}
