package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.webdriver.DriverFactory;
import config.DriverConfig;
import io.qameta.allure.selenide.AllureSelenide;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.sql.SQLOutput;

import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;
import static com.codeborne.selenide.logevents.SelenideLogger.addListener;
import static tests.helpers.AttachmentHelper.*;

public class TestBase extends TestData {

    static DriverConfig driverConfig = ConfigFactory.create(DriverConfig.class);
    public static String headerName;

    @BeforeAll
    static void setup() {

        addListener("AllureSelenide", new AllureSelenide());
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.browser = System.getProperty("web.browser", "chrome");

        String user = driverConfig.remoteWebUser();
        String pass = driverConfig.remoteWebPass();
        String headerName = System.getProperty("header.name");
        //"Student Registration Form"
        String remoteWebDriver = System.getProperty("remote.web.driver");
        if (remoteWebDriver != null)
            Configuration.remote = String.format(remoteWebDriver, user, pass);

        System.out.println(user);
        System.out.println(pass);
        System.out.println(remoteWebDriver);
        System.out.println(headerName);
        //System.out.println(String.format(remoteWebDriver, user, pass));
        System.out.printf((remoteWebDriver) + "%n", user, pass);
    }

    @AfterEach
    void afterEach() {
        attachScreenshot("Last screenshot");
        attachPageSource();
        attachAsText("Browser console logs", getConsoleLogs());

        if (System.getProperty("video.storage") != null)
            attachVideo();
        closeWebDriver();
    }
}

