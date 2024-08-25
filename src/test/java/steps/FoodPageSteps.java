package steps;

import io.cucumber.java.ru.И;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.support.ui.Select;
import pages.BasePage;
import pages.FoodPage;

import static org.junit.jupiter.api.Assertions.assertAll;

public class FoodPageSteps extends BasePage {

    FoodPage foodPage = new FoodPage();

    @И("Переход на страницу food")
    public void openFoodPage() {
        foodPage.openFoodPage();
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

    @И("Активировать чекбокс 'Экзотический'")
    public void enableExoticCheckbox() {
        foodPage.exoticCheckbox.click();
    }

    @И("Форма закрывается, товар {string} {string} {string} {string} отображается в списке")
    public void checkFoodIsAdded(String id, String ruName, String ruType, String isExotic) {
        assertAll("Проверка отображения добавленного товара в списке товаров",
                () -> Assertions.assertEquals(id, foodPage.newRowId.getText(), "Имя неверно"),
                () -> Assertions.assertEquals(ruName, foodPage.newRowName.getText(), "Имя неверно"),
                () -> Assertions.assertEquals(ruType, foodPage.newRowType.getText(), "Тип неверный"),
                () -> Assertions.assertEquals(isExotic, foodPage.newRowIsExotic.getText(), "Неверное поле экзотический"));
    }

}