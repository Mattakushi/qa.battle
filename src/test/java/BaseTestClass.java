import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;

@TestInstance(Lifecycle.PER_CLASS)
class BaseTestClass {

    private WebDriver driver;

    LoginPage loginPage;
    ProfilePage profilePage;
    MainPage mainPage;

    @BeforeAll
    void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        loginPage = new LoginPage(driver);
        profilePage = new ProfilePage(driver);
        mainPage = new MainPage(driver);
    }

    @AfterAll
    void tearDown() {
        driver.quit();
    }
}
