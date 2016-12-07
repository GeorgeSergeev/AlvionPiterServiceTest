
/**
 * Created by aburnasov on 10/5/2015.
 */
package ru.alvioneurope.ui.tests;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.*;
import net.thucydides.core.pages.Pages;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import qa.Author;
import qa.data.OrderData;
import qa.utils.WindowsHelper;
import ru.alvioneurope.steps.OrdersListSteps;
import ru.alvioneurope.ui.requirements.Application;
import ru.alvioneurope.steps.OrdersPageSteps;

import java.text.ParseException;

@Author("Alexey Burnasov <aburnasov@alvioneurope.ru>")
@Story(Application.CreateOrderFeature.OpenCreateOrderPage.class)
@RunWith(SerenityRunner.class)
public class OrdersPageTest {
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss";
    public static final String STATUS_DONE = "done";
    public static final String STATUS_SCHEDULED = "registered";

    @Managed(uniqueSession = true)
    public WebDriver webDriver;
    @ManagedPages
    public Pages pages;
    @Steps
    public OrdersPageSteps ordersPageSteps;
    @Steps
    public OrdersListSteps ordersListSteps;
    @Steps
    public WindowsHelper windowsHelper;

    @Category({
        qa.categories.priority.Smoke.class,
        qa.categories.browsers.Chrome.class,
    })
    @WithTags({
        @WithTag("tests:createOrder")
    })
    @Test
    public void singleLetterMessageTest() throws ParseException {
        ordersPageSteps.openOrdersPage();
        ordersPageSteps.ordersPageShouldBeOpened();

        ordersPageSteps.setDate("00:00");
        OrderData order = new OrderData();
        order.setMessage("a");
        ordersPageSteps.setMessage(order.getMessage());
        ordersPageSteps.clickSubmitButton();
        DateTime dateTime = new DateTime();
        order.setCreateDate(dateTime.toString(DATE_FORMAT));

        windowsHelper.refreshWindow();

        ordersListSteps.orderListShouldBePresent();
        int index = ordersListSteps.getOrderIndexByDate(order.getCreateDate());

        ordersListSteps.specifiedDateShouldBeEquals(order.getCreateDateFormat(), index);
        ordersListSteps.messageShouldBeEquals(order.getMessage(), index);
        ordersListSteps.statusShouldBeEquals(STATUS_DONE, index);
        ordersListSteps.resultShouldBeEquals(order.getMessage(), index);
    }

    @Category({
            qa.categories.priority.Smoke.class,
            qa.categories.browsers.Chrome.class,
    })
    @WithTags({
            @WithTag("tests:createOrder")
    })
    @Test
    public void tenLettersMessageTest() throws ParseException {
        ordersPageSteps.openOrdersPage();
        ordersPageSteps.ordersPageShouldBeOpened();

        ordersPageSteps.setDate("00:00");
        OrderData order = new OrderData();
        order.setMessage("asdfghjklzx");
        ordersPageSteps.setMessage(order.getMessage());
        ordersPageSteps.clickSubmitButton();
        DateTime dateTime = new DateTime();
        order.setCreateDate(dateTime.toString(DATE_FORMAT));

        windowsHelper.refreshWindow();

        ordersListSteps.orderListShouldBePresent();
        int index = ordersListSteps.getOrderIndexByDate(order.getCreateDate());

        ordersListSteps.specifiedDateShouldBeEquals(order.getCreateDateFormat(), index);
        ordersListSteps.messageShouldBeEquals(order.getMessage(), index);
        ordersListSteps.statusShouldBeEquals(STATUS_DONE, index);
        ordersListSteps.resultShouldBeEquals(order.getMessage(), index);
    }

    @Category({
            qa.categories.priority.Smoke.class,
            qa.categories.browsers.Chrome.class,
    })
    @WithTags({
            @WithTag("tests:createOrder")
    })
    @Test
    public void twentyLetterMessageTest() throws ParseException {
        ordersPageSteps.openOrdersPage();
        ordersPageSteps.ordersPageShouldBeOpened();

        ordersPageSteps.setDate("00:00");
        OrderData order = new OrderData();
        order.setMessage("qwertyuiopasdfghjkl");
        ordersPageSteps.setMessage(order.getMessage());
        ordersPageSteps.clickSubmitButton();
        DateTime dateTime = new DateTime();
        order.setCreateDate(dateTime.toString(DATE_FORMAT));

        windowsHelper.refreshWindow();

        ordersListSteps.orderListShouldBePresent();
        int index = ordersListSteps.getOrderIndexByDate(order.getCreateDate());

        ordersListSteps.specifiedDateShouldBeEquals(order.getCreateDateFormat(), index);
        ordersListSteps.messageShouldBeEquals(order.getMessage(), index);
        ordersListSteps.statusShouldBeEquals(STATUS_DONE, index);
        ordersListSteps.resultShouldBeEquals(order.getMessage(), index);
    }

    @Category({
            qa.categories.priority.Smoke.class,
            qa.categories.browsers.Chrome.class,
    })
    @WithTags({
            @WithTag("tests:createOrder")
    })
    @Pending
    @Test
    public void fourMinutesDateTest() throws ParseException {
        ordersPageSteps.openOrdersPage();
        ordersPageSteps.ordersPageShouldBeOpened();

        ordersPageSteps.setDate("00:04");
        OrderData order = new OrderData();
        order.setMessage("message");
        ordersPageSteps.setMessage(order.getMessage());
        ordersPageSteps.clickSubmitButton();
        DateTime dateTime = new DateTime().plusMinutes(4);
        order.setCreateDate(dateTime.toString(DATE_FORMAT));

        windowsHelper.refreshWindow();

        ordersListSteps.orderListShouldBePresent();
        int index = ordersListSteps.getOrderIndexByDate(order.getCreateDate());

        ordersListSteps.specifiedDateShouldBeEquals(order.getCreateDateFormat(), index);
        ordersListSteps.messageShouldBeEquals(order.getMessage(), index);
        ordersListSteps.statusShouldBeEquals(STATUS_SCHEDULED, index);
    }

    @Category({
            qa.categories.priority.Smoke.class,
            qa.categories.browsers.Chrome.class,
    })
    @WithTags({
            @WithTag("tests:createOrder")
    })
    @Pending
    @Test
    public void twoMinutesDateTest() throws ParseException {
        ordersPageSteps.openOrdersPage();
        ordersPageSteps.ordersPageShouldBeOpened();

        ordersPageSteps.setDate("00:02");
        OrderData order = new OrderData();
        order.setMessage("message");
        ordersPageSteps.setMessage(order.getMessage());
        ordersPageSteps.clickSubmitButton();
        DateTime dateTime = new DateTime().plusMinutes(2);
        order.setCreateDate(dateTime.toString(DATE_FORMAT));

        windowsHelper.refreshWindow();

        ordersListSteps.orderListShouldBePresent();
        int index = ordersListSteps.getOrderIndexByDate(order.getCreateDate());

        ordersListSteps.specifiedDateShouldBeEquals(order.getCreateDateFormat(), index);
        ordersListSteps.messageShouldBeEquals(order.getMessage(), index);
        ordersListSteps.statusShouldBeEquals(STATUS_SCHEDULED, index);
    }
}