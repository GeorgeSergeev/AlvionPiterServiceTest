package ru.alvioneurope.elements;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.serenitybdd.core.pages.WebElementFacade;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import qa.data.orderlist.OrderListColumn;
import ru.alvioneurope.pages.MainPage;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Класс, опичывающий Список заказов.
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
public class OrderListElementPage extends MainPage {
    private static final String ORDERS_COUNT_LOCATOR = "//*[@id='orderTable']//tr[@class='order-row']";
    private static final String ORDER_LIST_COLUMN_BY_INDEX_LOCATOR = ORDERS_COUNT_LOCATOR + "[%d]//td[%s]";
    private static final String ORDER_COLUMNT_LIST_LOCATOR = ORDERS_COUNT_LOCATOR + "//td[%s]";

    @FindBy(id = "orderTable")
    private WebElement orderListElement;

    public OrderListElementPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Проверка видимости списка заказов
     * @return
     */
    public boolean isOrderListPresent() {
        return waitForElementPresent(orderListElement);
    }

    /**
     * Получить номер из таблицы по индексу
     * @param index
     * @return
     */
    public String getNumberByIndex(int index) {
        String locator = String.format(ORDER_LIST_COLUMN_BY_INDEX_LOCATOR, index, OrderListColumn.NUMBER.getOrderListColumn());
        return $(locator).getText();
    }

    /**
     * Получить дату создания из таблицы по индексу
     * @param index
     * @return
     */
    public String getCreatedDateByIndex(int index) {
        String locator = String.format(ORDER_LIST_COLUMN_BY_INDEX_LOCATOR, index, OrderListColumn.CREATE_DATE.getOrderListColumn());
        return $(locator).getText();
    }

    /**
     * Получить дату, указанную при создании, из таблицы по индексу
     * @param index
     * @return
     */
    public Date getSpecifiedDateByIndex(int index) throws ParseException {
        String locator = String.format(ORDER_LIST_COLUMN_BY_INDEX_LOCATOR, index, OrderListColumn.SPECIFIED_DATE.getOrderListColumn());
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date parse = dateFormat.parse($(locator).getText());
        return parse;
    }

    /**
     * Получить входную строку из таблицы по индексу
     * @param index
     * @return
     */
    public String getInputMessageByIndex(int index) {
        String locator = String.format(ORDER_LIST_COLUMN_BY_INDEX_LOCATOR, index, OrderListColumn.INPUT_MESSAGE.getOrderListColumn());
        return $(locator).getText();
    }

    /**
     * Получить результат обработки из таблицы по индексу
     * @param index
     * @return
     */
    public String getresultByIndex(int index) {
        String locator = String.format(ORDER_LIST_COLUMN_BY_INDEX_LOCATOR, index, OrderListColumn.RESULT.getOrderListColumn());
        return $(locator).getText();
    }

    /**
     * Получить количесво заказов
     * @return
     */
    public int getOrderCount() {
        return findAll(By.xpath(ORDERS_COUNT_LOCATOR)).size();
    }

    public int getOrderIndexByDate(String date){
        int index = 1;
        String locator = String.format(ORDER_COLUMNT_LIST_LOCATOR, OrderListColumn.SPECIFIED_DATE);
        List<WebElementFacade> elements = findAll(locator);
        for (WebElementFacade element : elements) {
            if (element.getText().equals(date)) {
                return index;
            }
            index++;
        }
        return 0;
    }

    /**
     * Получить статус заказа
     * @param index
     * @return
     */
    public String getStatus(int index) {
        String locator = String.format(ORDER_LIST_COLUMN_BY_INDEX_LOCATOR, index, OrderListColumn.STATUS.getOrderListColumn());
        return $(locator).getText();
    }
}
