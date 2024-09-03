package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver;
    private static Properties properties = new Properties();


    @Before
    public void beforeEach() throws IOException {

        InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(input);

        properties.forEach((key, value) -> System.getProperties()
                .forEach((custom_key, custom_value) -> {
                    if (custom_key.toString().equals(key.toString())) {
                        properties.setProperty(key.toString(), custom_value.toString());
                    }
                })
        );

        if (properties.getProperty("execution.mode").equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(properties.getProperty("browser.type"));
            capabilities.setVersion(properties.getProperty("browser.version"));
            capabilities.setCapability("selenoid:options", Map.of(
                    "enableVNC", true,
                    "enableVideo", false));
            driver = new RemoteWebDriver(URI.create(properties.getProperty("selenoid.url")).toURL(),
                    capabilities);
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://149.154.71.152:8080/food");
            driver.manage().window().maximize();
        } else if (properties.getProperty("execution.mode").equals("local")) {
                        System.setProperty("webdriver.chrome.driver",  properties.getProperty("local.driver.path"));
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://149.154.71.152:8080/food");
            driver.manage().window().maximize();
        } else {
            System.out.println("unknown mode");
        }

    }

    @After
    public void afterEach() {
        if (driver != null) {
            driver.quit();
        }
    }

}
