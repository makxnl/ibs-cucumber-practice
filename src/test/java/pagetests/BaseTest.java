package pagetests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest {

    public static WebDriver driver = new ChromeDriver();

    public BaseTest() {
        System.setProperty("webdriver.chrome.driver", "C:\\ideaProject"
                + "\\ibsTest1\\src\\test\\resources\\chromedriver-win64\\chromedriver.exe");
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

}
