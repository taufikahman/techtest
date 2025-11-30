# Appium Cucumber Test Framework for SauceLabs Demo App

This project demonstrates automated testing of the [SauceLabs My Demo App for Android](https://github.com/saucelabs/my-demo-app-android) using Appium with Cucumber BDD framework.


## Setup Instructions

### 1. Install Appium
```bash
npm install -g appium
npm install -g appium-doctor

# Verify installation
appium-doctor --android
```

### 2. Download the SauceLabs Demo App
The APK file is already included in the project at `src/test/resources/builds/mda-1.0.13-15.apk`

### 3. Set up Android Emulator
Create an Android Virtual Device (AVD) with these specifications:
- **Device Name**: `Pixel_3a_API_34_x86_64` (or update the device name in BaseDriver.java)
- **API Level**: 34 (Android 14)
- **Architecture**: x86_64

### 4. Install Dependencies
```bash
mvn clean install
```

## Running Tests

### 1. Start Appium Server
```bash
appium
```

### 2. Start Android Emulator
```bash
# Start your AVD
emulator -avd Pixel_3a_API_34_x86_64
```

### 3. Run All Tests
```bash
mvn test
```

### 4. Run Specific Feature
```bash
# Run only login tests
mvn test -Dcucumber.filter.tags="@login"

# Run only product details tests
mvn test -Dcucumber.filter.tags="@products"
```

## Configuration

### Device Configuration
Update device settings in `BaseDriver.java`:
```java
capabilities.setCapability("deviceName", "YOUR_DEVICE_NAME");
capabilities.setCapability("platformName", "Android");
capabilities.setCapability("automationName", "UiAutomator2");
```

### App Configuration
The app package and activity are configured for SauceLabs Demo App:
```java
capabilities.setCapability("appPackage", "com.saucelabs.mydemoapp.android");
capabilities.setCapability("appActivity", "com.saucelabs.mydemoapp.android.view.activities.SplashActivity");
```

## Reports

Test reports are generated in:
- **HTML Report**: `target/cucumber-reports/cucumber-html-report.html`
- **Console Output**: Detailed step execution logs

## Dependencies

- **Appium Java Client**: 7.6.0
- **Selenium WebDriver**: 3.141.59 (compatible with JDK 8)
- **Cucumber Java**: 7.15.0
- **JUnit**: 4.13.2
- **TestNG**: 7.8.0

## License

This project is for hiring and testing purposes.
# techtest
