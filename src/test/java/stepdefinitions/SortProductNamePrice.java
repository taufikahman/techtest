package stepdefinitions;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortProductNamePrice {

    static AppiumDriver driver;

    @Given("user is on the products page")
    public void user_is_on_the_products_page() throws MalformedURLException, InterruptedException {

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

        WebElement title = driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/productTV"));
        Assert.assertTrue(title.isDisplayed(), "Products page is NOT displayed");
    }

    @When("user selects sort option Name Z to A")
    public void user_selects_sort_option_Name_Z_to_A() {

        // Klik tombol filter / sort (sesuaikan locator)
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/sortIV")).click();

        // Klik berdasarkan teks (Android)
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Name - Ascending\"]")).click();
    }


    @Then("products should be displayed in descending alphabetical order")
    public void products_should_be_displayed_in_descending_alphabetical_order() {

        List<WebElement> nameElements =
                driver.findElements(AppiumBy.xpath("//android.widget.TextView[@content-desc=\"Product Title\" and @text=\"Test.allTheThings() T-Shirt (yellow)\"]"));

        List<String> names = extractNames(nameElements);

        List<String> sorted = new ArrayList<>(names);
        Collections.sort(sorted, Collections.reverseOrder());

        Assert.assertEquals(names, sorted,
                "Products are NOT sorted correctly from Z to A");
    }

    @When("user selects sort option Price Low to High")
    public void user_selects_sort_option_Price_Low_to_High() {

        // Klik tombol filter / sort (sesuaikan locator)
        driver.findElement(AppiumBy.id("com.saucelabs.mydemoapp.android:id/sortIV")).click();

        // Klik berdasarkan teks (Android)
        driver.findElement(AppiumBy.xpath("//android.widget.TextView[@text=\"Name - Ascending\"]")).click();
    }

    @Then("products should be displayed from the lowest price to the highest price")
    public void products_should_be_displayed_from_the_lowest_price_to_the_highest_price() {
        List<WebElement> priceElements =
                driver.findElements(AppiumBy.id("com.saucelabs.mydemoapp.android:id/priceAscCL"));
        List<Double> prices = extractPrices(priceElements);

        List<Double> sortedPrices = new ArrayList<>(prices);
        Collections.sort(sortedPrices); // Low → High

        Assert.assertEquals(prices, sortedPrices,
                "Products are NOT sorted correctly from low to high price");
    }


    // Utilities


    private List<String> extractNames(List<WebElement> elements) {
        List<String> list = new ArrayList<>();
        for (WebElement e : elements) {
            list.add(e.getText().trim());
        }
        return list;
    }

    private List<Double> extractPrices(List<WebElement> elements) {
        List<Double> list = new ArrayList<>();
        for (WebElement e : elements) {
            String text = e.getText().replace("$", "").trim();
            list.add(Double.parseDouble(text));
        }
        return list;
    }
}
