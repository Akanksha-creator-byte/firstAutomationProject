package tests;

import Base.BaseTest;
import page.LoginPage;
import Utils.ExcelReader;
import Utils.ExtentReportManager;
import Utils.ScreenshotUtil;

import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.testng.Assert;

import com.aventstack.extentreports.*;

public class LoginTest extends BaseTest {
    ExtentReports extent;
    ExtentTest test;

    @BeforeSuite
    public void startReport() {
        extent = ExtentReportManager.getReportInstance();
        log.info("Extent report initialized");
    }

    @Test(dataProvider="loginData")
    public void loginTest(String username, String password) {
        test = extent.createTest("Login Test for: " + username);
        LoginPage login = new LoginPage(driver);
        login.login(username, password);

        // Dummy assertion for example
     
    try {
    	   Assert.assertTrue(driver.findElement(By.xpath("//*[@id=\"email_container\"]/div[2]/text()")).isDisplayed(), "The email address or mobile number you entered isn't connected to an account.");
           test.pass("Login successful");
       
    } catch (AssertionError e) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, "LoginTest_" + username);
        test.fail("Login failed for user: " + username)
            .addScreenCaptureFromPath(screenshotPath);
        throw e; // rethrow so TestNG marks it failed
    }
}

    @DataProvider(name="loginData")
    public Object[][] getLoginData() {
        return ExcelReader.getData("testdata/LoginData.xlsx", "Sheet1");
    }

    @AfterSuite
    public void endReport() {
        extent.flush();
    }
}
