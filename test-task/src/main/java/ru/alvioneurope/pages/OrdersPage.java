package ru.alvioneurope.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import net.thucydides.core.annotations.At;
import net.thucydides.core.annotations.DefaultUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Класс,описывающий страницу создания заказа
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
@DefaultUrl("/frontend/")
@At(urls = {"#HOST/frontend/"})
public class OrdersPage extends MainPage {
    private static final String TITLE = "Создание заказа";
    private static final String TITLE_LOCATOR = "//*[@class = 'form-header'][text() = '%s']";

    @FindBy(id = "datetimeInput")
    private WebElement orderTimeField;
    @FindBy(id = "messageInput")
    private WebElement messageField;
    @FindBy(id = "orderSubmit")
    private WebElement submitButton;
    @FindBy(id = "ui-datepicker-div")
    private WebElement datePicker;

    public  OrdersPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Проверка видипости страницы
     * @return
     */
    public boolean isOrderPagePresent() {
        String locator = String.format(TITLE_LOCATOR, TITLE);
        return waitForElementPresent(By.xpath(locator));
    }

    /**
     * Проверка невидимости диалога выбора даты
     * @return
     */
    public boolean isDatePockerNotPresent() {
        return waitForElementNotPresent(datePicker);
    }

    /**
     * Заполнить поле "Время обработки заказа"
     * @param value
     */
    public void fillOrderTimeField(String value) {
        orderTimeField.sendKeys(value);
    }

    /**
     * Заполнить поле "Сообщение"
     * @param value
     */
    public void fillMessageField(String value) {
        messageField.click();
        messageField.sendKeys(value);
    }

    /**
     * Клик "Submit"
     */
    public void clickSubmitButton() {
        submitButton.click();
    }
}
