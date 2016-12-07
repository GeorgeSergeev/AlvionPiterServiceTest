package ru.alvioneurope.steps;

import net.thucydides.core.annotations.Step;
import net.thucydides.core.steps.ScenarioSteps;
import ru.alvioneurope.elements.OrderListElementPage;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
public class OrdersListSteps extends ScenarioSteps {

    private OrderListElementPage getOrderListElementPage() {
        return getPages().onSamePage().get(OrderListElementPage.class);
    }

    @Step(callNestedMethods = false)
    public void orderListShouldBePresent() {
        assertTrue("Список заказов должен отображаться.", getOrderListElementPage().isOrderListPresent());
    }

    @Step(callNestedMethods = false)
    public void createdDateShouldBeEquals(String date, int index) {
        assertEquals("Дата создания сохранена не правильно.",
                date, getOrderListElementPage().getCreatedDateByIndex(index));
    }

    @Step(callNestedMethods = false)
    public void specifiedDateShouldBeEquals(Date date, int index) throws ParseException {
        assertTrue("Дата, указанная при создании сохранена не правильно.",
                date.getTime() < getOrderListElementPage().getSpecifiedDateByIndex(index).getTime() + 1000 &&
                        date.getTime() > getOrderListElementPage().getSpecifiedDateByIndex(index).getTime() - 1000);
    }

    @Step(callNestedMethods = false)
    public void statusShouldBeEquals(String status, int index) {
        waitABit(5000);
        assertEquals("Статус заказа не правильный.",
                status, getOrderListElementPage().getStatus(index));
    }

    @Step(callNestedMethods = false)
    public void messageShouldBeEquals(String message, int index) {
        assertEquals("Сообщение сохранена не правильно.",
                message, getOrderListElementPage().getInputMessageByIndex(index));
    }

    @Step(callNestedMethods = false)
    public void resultShouldBeEquals(String result, int index) {
        int count = getOrderListElementPage().getOrderCount();
        if (count == 1) {
            assertEquals("Результат обработки сохранена не правильный.",
                    result, getOrderListElementPage().getresultByIndex(index));
        } else {
            for (int i = 1; i <= count; i++) {
                result = result.concat(getOrderListElementPage().getInputMessageByIndex(index - i));
                if(i == 3) {
                    break;
                }
            }
            assertEquals("Результат обработки сохранена не правильный.",
                    result, getOrderListElementPage().getresultByIndex(index));
        }
    }

    public int getOrdersCount() {
        return getOrderListElementPage().getOrderCount();
    }

    public int getOrderIndexByDate(String date) {
        return getOrderListElementPage().getOrderIndexByDate(date);
    }
}
