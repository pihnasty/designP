package testNG;

import org.junit.Assert;
import org.testng.annotations.Test;

public class Ignore {

    @Test(dataProvider = "testCases", enabled = false)
    public void testGetEvaluationTree(String testCase) {
        Assert.assertEquals("", "".toString().trim());

    }

}
