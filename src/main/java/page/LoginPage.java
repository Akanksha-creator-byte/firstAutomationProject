package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginPage {
    WebDriver driver;
    // Initialize logger
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    @FindBy(id="email") WebElement txtUsername;
    @FindBy(id="pass") WebElement txtPassword;
    @FindBy(name="login") WebElement btnLogin;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
    	 log.info("Attempting to login with Username: " + username);
        txtUsername.sendKeys(username);
        log.debug("Entered username");
        log.info("Attempting to login with password: " + password);
        txtPassword.sendKeys(password);
        log.debug("Entered password");
        btnLogin.click();
        log.info("Clicked login button");
    }
}
