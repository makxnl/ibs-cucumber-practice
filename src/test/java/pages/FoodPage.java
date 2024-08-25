package pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import static org.junit.jupiter.api.Assertions.assertAll;
import static pagetests.BaseTest.driver;

public class FoodPage extends BasePage {

    @FindBy(xpath = "//div[@class='container-fluid']/h5")
    public WebElement foodPageTitle;

    @FindBy(xpath = "//div[@class='btn-grou mt-2 mb-2']/button")
    public WebElement addFoodBtn;

    @FindBy(xpath = "//h5[@id='editModalLabel']")
    public WebElement addFormTitle;

    @FindBy(xpath = "//input[@id='name']")
    public WebElement foodNameField;

    @FindBy(xpath = "//select[@name='type']")
    public WebElement foodList;

    @FindBy(xpath = "//button[@id='save']")
    public WebElement saveFoodBtn;

    @FindBy(xpath = "//th[@scope='row' and text()='5']")
    public WebElement newRowId;

    @FindBy(xpath = "//th[@scope='row' and text()='5']/following::td[1]")
    public WebElement newRowName;

    @FindBy(xpath = "//th[@scope='row' and text()='5']/following::td[2]")
    public WebElement newRowType;

    @FindBy(xpath = "//th[@scope='row' and text()='5']/following::td[3]")
    public WebElement newRowIsExotic;

    @FindBy(xpath = "//input[@type='checkbox']")
    public WebElement exoticCheckbox;


    public FoodPage() {
        PageFactory.initElements(driver, this);
    }

    public void openFoodPage() {
        driver.get("http://localhost:8080/food");
        driver.manage().window().maximize();
    }

    public FoodPage checkFoodPageTitleIsVisible(String title) {
        Assertions.assertEquals(title, foodPageTitle.getText(), "Некорректный заголовок 'Список товаров'");
        return this;
    }

    public FoodPage clickToAddBtn() {
        addFoodBtn.click();
        return this;
    }

    public FoodPage checkAddFormTitleIsVisible(String title) {
        waitUntilElementToBeVisible(addFormTitle);
        Assertions.assertEquals(title, addFormTitle.getText(), "Некорректный заголовок 'Добавление товара'");
        return this;
    }

    public FoodPage fillNameField(String value) {
        fillInputField(foodNameField, value);
        return this;
    }

    public FoodPage selectVegetableFromDropdownList(String foodType) {
        Select select = new Select(foodList);
        select.selectByVisibleText(foodType);
        return this;
    }

    public FoodPage clickToSaveBtn() {
        saveFoodBtn.click();
        return this;
    }

    public FoodPage enableExoticCheckbox() {
        exoticCheckbox.click();
        return this;
    }

    public FoodPage checkFoodIsAdded(String id, String ruName, String ruType, String isExotic) {
        assertAll("Проверка отображения добавленного товара в списке товаров",
                () -> Assertions.assertEquals(id, newRowId.getText(), "Имя неверно"),
                () -> Assertions.assertEquals(ruName, newRowName.getText(), "Имя неверно"),
                () -> Assertions.assertEquals(ruType, newRowType.getText(), "Тип неверный"),
                () -> Assertions.assertEquals(isExotic, newRowIsExotic.getText(), "Неверное поле экзотический"));
        return this;
    }

}