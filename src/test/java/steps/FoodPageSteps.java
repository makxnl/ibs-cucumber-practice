package steps;

import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import pages.FoodPage;

import java.util.concurrent.TimeUnit;

import static pagetests.BaseTest.driver;

public class FoodPageSteps extends BasePage {

    FoodPage foodPage = new FoodPage();

    @И("Переход на страницу food")
    public void openFoodPage() {
        driver.get("http://localhost:8080/food");
        driver.manage().window().maximize();

    }


    @И("Открыта страница {string}")
    public void checkFoodPageTitleIsVisible(String title) {
        Assertions.assertEquals(title, foodPage.foodPageTitle.getText(), "Некорректный заголовок 'Список товаров'");
    }

    @И("Нажать кнопку 'Добавить'")
    public void clickToAddBtn() {
        foodPage.addFoodBtn.click();
    }

    @И("Открыта форма {string}")
    public void checkAddFormTitleIsVisible(String title) {
        waitUntilElementToBeVisible(foodPage.addFormTitle);
        Assertions.assertEquals(title, foodPage.addFormTitle.getText(), "Некорректный заголовок 'Добавление товара'");
    }

    @И("Заполнить наименование значением {string}")
    public void fillNameField(String value) {
        fillInputField(foodPage.foodNameField, value);
    }


    @И("Выбрать в выпадающем списке тип {string}")
    public void selectVegetableFromDropdownList(String foodType) {
        Select select = new Select(foodPage.foodList);
        select.selectByVisibleText(foodType);
    }

    @И("Нажать кнопку 'Сохранить'")
    public void clickToSaveBtn() {
        foodPage.saveFoodBtn.click();
    }

//    public void enableExoticCheckbox() {
//        exoticCheckbox.click();
//    }
//
//    public void checkFoodIsAdded(String id, String ruName, String ruType, String isExotic) {
//        assertAll("Проверка отображения добавленного товара в списке товаров",
//                () -> Assertions.assertEquals(id, newRowId.getText(), "Имя неверно"),
//                () -> Assertions.assertEquals(ruName, newRowName.getText(), "Имя неверно"),
//                () -> Assertions.assertEquals(ruType, newRowType.getText(), "Тип неверный"),
//                () -> Assertions.assertEquals(isExotic, newRowIsExotic.getText(), "Неверное поле экзотический"));
//    }

}