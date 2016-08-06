package tasks.t9986_CreatingTestVersionArchiverGzipBzipLzop.com.amazon.dwms160804;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

/**
 * Created by yaroshenko.y on 7/31/2016.
 */
public class BaseTest {

        @BeforeSuite
        public void setGlobalParameters(ITestContext context) {
        }

        @BeforeMethod
        public void init(ITestContext context) {

        }

        @AfterMethod
        public void down(ITestResult result) {

        }

        @AfterSuite
        public void closeSuite() {

        }


}
