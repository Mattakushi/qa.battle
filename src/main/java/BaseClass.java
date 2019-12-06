import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.junit.jupiter.api.extension.AfterAllCallback;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class BaseClass implements BeforeAllCallback, BeforeTestExecutionCallback, AfterAllCallback, AfterTestExecutionCallback {

    private static ExtentReports reports;
    private static ExtentTest test;

    @Override
    public void beforeAll(ExtensionContext context) throws Exception {
        String filename = System.getProperty("user.dir") + "/test-output/" + context.getDisplayName() + "_Results.html";
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(filename);
        reports = new ExtentReports();
        reports.attachReporter(htmlReporter);
    }

    @Override
    public void beforeTestExecution(ExtensionContext context) throws Exception {
        String name = context.getParent().get().getDisplayName() + " : " + context.getDisplayName();
        test = reports.createTest(name);

        test.log(Status.INFO, name + " - started");

    }

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        String name = context.getParent().get().getDisplayName() + " : " + context.getDisplayName();
        if (!context.getExecutionException().isPresent()) {
            test.pass(name + " - passed");
        } else {
            test.fail(context.getExecutionException().get().getLocalizedMessage());
        }
    }

    @Override
    public void afterAll(ExtensionContext context) throws Exception {
        reports.flush();
    }


}
