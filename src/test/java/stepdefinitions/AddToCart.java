package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import testdata.LoginData;

import java.net.MalformedURLException;
import java.net.URL;

public class AddToCart {

    static AppiumDriver driver;

    @Given("user is on the product page for Sauce Lab Back Packs")
    public void user_is_on_the_product_page_for_backpacks() throws Exception, MalformedURLException, InterruptedException  {

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
        WebElement usernameField =
                driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/nameET"));

        WebElement passwordField =
                driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/passwordET"));

        usernameField.clear();
        usernameField.sendKeys(LoginData.USERNAME_VALID);

        passwordField.clear();
        passwordField.sendKeys(LoginData.PASSWORD_VALID);
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/loginBtn"))
                .click();
        Thread.sleep(5000);

        // Klik produk "Sauce Lab Back Packs"
        driver.findElement(AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"Product Image\"])[1]")).click();

        Thread.sleep(1000);

        WebElement title = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
        Assert.assertTrue(title.isDisplayed(), "Product page not opened!");
    }

    @When("user selects color Blue")
    public void user_selects_color_blue() {

        // Klik pilihan warna Blue
        driver.findElement(AppiumBy.xpath("//android.widget.ImageView[@content-desc=\"Blue color\"]")).click();
    }

    @And("user selects quantity {int}")
    public void user_selects_quantity(int qty) {

        // Adjust quantity using +/- buttons
        WebElement plusBtn = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/plusIV"));

        for (int i = 1; i < qty; i++) {
            plusBtn.click();
        }
    }

    @And("user adds the product to the cart")
    public void user_adds_the_product_to_the_cart() throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(AppiumBy.xpath("//android.widget.Button[@content-desc=\"Tap to add product to cart\"]")).click();
    }

    @Then("the product should be added with quantity {int} and color {string}")
    public void the_product_should_be_added_with_quantity_and_color(Integer qty, String color) throws InterruptedException{

        // Navigate to cart
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/cartIV")).click();

    }
}
