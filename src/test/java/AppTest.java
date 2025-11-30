import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class AppTest {

    static AppiumDriver driver;

    public static void main(String[] args) throws MalformedURLException, InterruptedException {
        openMobileApp();

    }

    public static void openMobileApp() throws MalformedURLException, InterruptedException {

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

    }
}
