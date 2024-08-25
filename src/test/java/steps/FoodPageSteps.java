package steps;

import io.cucumber.java.ru.И;
import pages.FoodPage;

public class FoodPageSteps {

    FoodPage foodPage = new FoodPage();

    @И("Открыта страница {string}")
    public void checkFoodPageTitleIsVisible(String title) {
        foodPage.checkFoodPageTitleIsVisible(title);
    }

    @И("Нажать кнопку 'Добавить'")
    public void clickToAddBtn() {
        foodPage.clickToAddBtn();
    }

    @И("Открыта форма {string}")
    public void checkAddFormTitleIsVisible(String title) {
        foodPage.checkAddFormTitleIsVisible(title);
    }

    @И("Заполнить наименование значением {string}")
    public void fillNameField(String value) {
        foodPage.fillNameField(value);
    }


    @И("Выбрать в выпадающем списке тип {string}")
    public void selectVegetableFromDropdownList(String foodType) {
        foodPage.selectVegetableFromDropdownList(foodType);
    }

    @И("Нажать кнопку 'Сохранить'")
    public void clickToSaveBtn() {
        foodPage.clickToSaveBtn();
    }

    @И("Активировать чекбокс 'Экзотический'")
    public void enableExoticCheckbox() {
        foodPage.enableExoticCheckbox();
    }

    @И("Форма закрывается, товар {string} {string} {string} {string} отображается в списке")
    public void checkFoodIsAdded(String id, String ruName, String ruType, String isExotic) {
        foodPage.checkFoodIsAdded(id, ruName, ruType, isExotic);
    }

}