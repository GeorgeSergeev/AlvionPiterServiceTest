package ru.alvioneurope.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.annotations.StepGroup;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.steps.ScenarioSteps;
import qa.utils.ResolutionHelper;
import ru.alvioneurope.pages.OrdersPage;

import static org.junit.Assert.assertTrue;

public class OrdersPageSteps extends ScenarioSteps {
    @Steps
    public ResolutionHelper resolutionHelper;

    private OrdersPage getOrdersPage() {
        return getPages().onSamePage().getAt(OrdersPage.class);
    }

    /**
     * Открыть страницу создания заказа
     */
    @Step(callNestedMethods = false)
    public void openPage() {
        getOrdersPage().open();
    }

    @StepGroup
    public void openOrdersPage() {
        resolutionHelper.setResolution();

        openPage();
        ordersPageShouldBeOpened();
    }

    /**
     * Ввести сообщение
     * @param value
     */
    @Step(callNestedMethods = false)
    public void setMessage(String value) {
        getOrdersPage().fillMessageField(value);
    }

    /**
     * Ввести время создания заказа
     * @param value
     */
    @Step(callNestedMethods = false)
    public void setDate(String value) {
        getOrdersPage().fillOrderTimeField(value);
    }

     /**
     * Проверяем, что страница создания заказа открыта
     */
    @Step(callNestedMethods = false)
    public void ordersPageShouldBeOpened() {
        assertTrue("Не открылась страница создания заказа.", getOrdersPage().isOrderPagePresent());
    }

    @Step(callNestedMethods = false)
    public void datePickerShouldNotBePresent() {
        assertTrue("Диалог выбора даты не закрылся.", getOrdersPage().isDatePockerNotPresent());
    }

    /**
     *  клик, submit
     */
    @StepGroup
    public void clickSubmitButton() {
        datePickerShouldNotBePresent();
        getOrdersPage().clickSubmitButton();
    }
}
