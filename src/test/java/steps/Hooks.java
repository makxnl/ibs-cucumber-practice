package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;

import static pagetests.BaseTest.driver;

public class Hooks {

    @After
    public void afterEach() {
        driver.quit();
    }

}
