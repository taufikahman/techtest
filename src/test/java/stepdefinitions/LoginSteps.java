package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.DesiredCapabilities;
import testdata.LoginData;
import org.openqa.selenium.WebElement;
import org.testng.Assert;


import java.net.MalformedURLException;
import java.net.URL;

public class LoginSteps {

    static AppiumDriver driver;

    @Given("user navigates to the login page")
    public void user_navigates_to_the_login_page() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("appium:deviceName", "sdk_gphone64_arm64");
        caps.setCapability("platformName", "Android");
        caps.setCapability("appium:platformVersion", "15");
        caps.setCapability("appium:automationName", "UiAutomator2");
        caps.setCapability("appium:appPackage", "com.saucelabs.mydemoapp.android");
        caps.setCapability("appium:appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");


        URL url = new URL("http://127.0.0.1:4723");
        driver = new AppiumDriver(url, caps);

        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"View menu\"]")).click();
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Login Menu Item\"]")).click();
        Thread.sleep(4000);
    }
    @When("user enters a valid username and password")
    public void user_enters_a_valid_username_and_password(){
        WebElement usernameField =
                driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET"));

        WebElement passwordField =
                driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET"));

        usernameField.clear();
        usernameField.sendKeys(LoginData.USERNAME_VALID);

        passwordField.clear();
        passwordField.sendKeys(LoginData.PASSWORD_VALID);

    }
    @And("user taps the Login button")
    public void user_taps_the_Login_button(){

        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn"))
                .click();
    }
    @Then("user should be logged in successfully")
    public void user_should_be_logged_in_successfully() throws InterruptedException {
        Thread.sleep(2000);

        // Validasi muncul icon profile / logged-in state
        driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"View menu\"]")).click();
        WebElement logoutIcon =
                driver.findElement(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Logout Menu Item\"]"));

        Assert.assertTrue(logoutIcon.isDisplayed(), "User FAILED to login");
    }
}
