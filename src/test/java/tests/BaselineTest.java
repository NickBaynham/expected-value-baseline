package tests;

import baseline.Expectations;
import baseline.GlobalManager;
import baseline.TestManager;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BaselineTest {

    @Test
    public void test1() {
        verifyValue(new Throwable().getStackTrace()[0].getMethodName(), "121.00", "Actual value didn't match.");
    }

    @Test
    public void test2() {
        verifyValue(new Throwable().getStackTrace()[0].getMethodName(), "145.00", "Actual value didn't match expected value.");
    }

    @Test
    public void test3() {
        verifyValue(new Throwable().getStackTrace()[0].getMethodName(), "155.00", "Actual value didn't match expected value.");
    }

    private void verifyValue(String testName, String actualValue, String message) {
        System.out.println("Verify Value in: " + testName);
        Expectations expectations = new TestManager(testName);
        assertEquals(expectations.expectedValue(new Throwable().getStackTrace()[0].getMethodName(), actualValue), actualValue, message);
    }

    @BeforeSuite
    public void before() {
        GlobalManager.setBaselinePath("src/test/resources/baseline/");
    }
}
