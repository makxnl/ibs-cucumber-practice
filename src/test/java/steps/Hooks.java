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
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Hooks {

    public static WebDriver driver;
    private static Properties properties = new Properties();


    @Before
    public void beforeEach() throws IOException {

        InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties");
        properties.load(input);

        if (properties.getProperty("execution.mode").equals("remote")) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(properties.getProperty("browser"));
            capabilities.setVersion("109.0");
            capabilities.setCapability("enableVNC", true);
            capabilities.setCapability("enableVideo", false);
            driver = new RemoteWebDriver(URI.create(properties.getProperty("selenoid.url")).toURL(),
                    capabilities);
        } else {
            System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver-win64/chromedriver.exe");
            driver = new ChromeDriver();
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            driver.get("http://149.154.71.152:8080/food");
            driver.manage().window().maximize();
        }

    }

    @After
    public void afterEach() {
        driver.quit();
    }

}
