import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

@ExtendWith(BaseClass.class)
class MainTest extends BaseTestClass {

    @BeforeEach
    void loginAndOpen() {
        loginPage.open()
                .cookieLogin();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName(value = "Download article data from advertisers")
    void advertisersDownload(Integer num) {
        mainPage.openAdvertisers()
                .selectAdvertisersArticle(num)
                .downloadInfo()
                .verifySavedFile();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName(value = "Download article data from publishers")
    void publishersDownload(Integer num) {
        mainPage.openPublishers()
                .selectPublishersArticle(num)
                .downloadInfo()
                .verifySavedFile();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName(value = "Download article data from top level clients")
    void topLevelClientsDownload(Integer num) {
        mainPage.openTopLevelClients()
                .selectTopLevelClientsArticle(num)
                .downloadInfo()
                .verifySavedFile();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName(value = "Save article and download data from advertisers")
    void advertisersDownloadSaved(Integer num) {
        mainPage.openAdvertisers()
                .selectAdvertisersArticle(num)
                .downloadInfo()
                .verifySavedFile();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName(value = "Save article and download data from publishers")
    void publishersDownloadSaved(Integer num) {
        mainPage.openPublishers()
                .selectPublishersArticle(num)
                .downloadInfo()
                .verifySavedFile();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName(value = "Save article and download data from top level clients")
    void topLevelClientsDownloadSaved(Integer num) {
        mainPage.openTopLevelClients()
                .selectTopLevelClientsArticle(num)
                .scrollDownTextarea()
                .moveToSaved()
                .openFirstSavedArticle()
                .downloadInfo()
                .verifySavedFile();
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName(value = "Save and remove article from advertisers")
    void saveAndRemoveAdvertisersArticle(int num) {
        mainPage.openAdvertisers()
                .selectAdvertisersArticle(num)
                .scrollDownTextarea()
                .moveToSaved()
                .checkFirstSavedArticleIsExist()
                .checkAdvertisersArticles(1)
                .openFirstSavedArticle()
                .removedFromSaved()
                .checkAdvertisersArticles(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2})
    @DisplayName(value = "Save and remove article from publishers")
    void saveAndRemovePublishersArticle(int num) {
        mainPage.openPublishers()
                .selectPublishersArticle(num)
                .scrollDownTextarea()
                .moveToSaved()
                .checkFirstSavedArticleIsExist()
                .checkPublishersArticles(1)
                .openFirstSavedArticle()
                .removedFromSaved()
                .checkPublishersArticles(2);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName(value = "Save and remove article from top level clients")
    void saveAndRemoveTopLevelClientsArticle(int num) {
        mainPage.openTopLevelClients()
                .selectTopLevelClientsArticle(num)
                .scrollDownTextarea()
                .moveToSaved()
                .checkFirstSavedArticleIsExist()
                .checkTopLevelClientsArticles(9)
                .openFirstSavedArticle()
                .removedFromSaved()
                .checkTopLevelClientsArticles(10);
    }

    @Test
    @DisplayName(value = "Check advertisers articles size is 2")
    void checkAdvertisersArticlesSize() {
        mainPage.openAdvertisers()
                .checkAdvertisersArticles(2);
    }

    @Test
    @DisplayName(value = "Check publishers articles size is 2")
    void checkPublishersArticlesSize() {
        mainPage.openPublishers()
                .checkPublishersArticles(2);
    }

    @Test
    @DisplayName(value = "Check top level clients articles size is 10")
    void checkTopLevelClientsArticlesSize() {
        mainPage.openTopLevelClients()
                .checkTopLevelClientsArticles(10);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:300", "25:350", "50:400", "75:450", "100:500"}, delimiter = ':')
    @DisplayName(value = "Check image size after move slider")
    void moveImageSliderInArticle(int x, int size) {
        mainPage.openPublishers()
                .selectPublishersArticle(2)
                .moveSlider(x)
                .verifyImageSize(size);
    }


    @AfterEach
    void logout() {
        loginPage.logout();
    }

}
