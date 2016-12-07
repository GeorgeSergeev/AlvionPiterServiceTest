package ru.alvioneurope.api;

import com.jayway.restassured.response.Response;
import org.joda.time.DateTime;
import org.junit.Test;
import qa.data.OrderData;
import qa.utils.RestUtility;

import java.text.SimpleDateFormat;

import static org.hamcrest.Matchers.hasItems;
import static org.junit.Assert.*;

/**
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
public class OrdersRestTest extends BaseRestTest {
    public static final String DATE_FORMAT = "dd-MM-yyyy HH:mm:ss ZZ";

    public RestUtility restUtility;

    @Test
    public void addOrderTest() {
        restUtility = new RestUtility();
        DateTime dateTime = new DateTime();
        OrderData order = new OrderData();
        order.setMessage("a");
        order.setCreateDate(dateTime.toString(DATE_FORMAT));
        Response response = restUtility.addTask(order);
        response.then().statusCode(200);
    }

    @Test
    public void orderStatusDoneTest() {
        restUtility = new RestUtility();
        DateTime dateTime = new DateTime();
        OrderData order = new OrderData();
        order.setMessage("a");
        order.setCreateDate(dateTime.toString(DATE_FORMAT));
        Response response = restUtility.addTask(order);
        int orderId = response.getBody().jsonPath().get("id");

        response = restUtility.getTaskStatus();
        response.then().body(String.format("findAll {it.id = %d}.status", orderId), hasItems("done"));
    }

    @Test
    public void orderStatusScheduledTest() {
        restUtility = new RestUtility();
        DateTime dateTime = new DateTime().plus(5);
        OrderData order = new OrderData();
        order.setMessage("a");
        order.setCreateDate(dateTime.toString(DATE_FORMAT));
        Response response = restUtility.addTask(order);

        int orderId = response.getBody().jsonPath().get("id");

        response = restUtility.getTaskStatus();
        response.then().body(String.format("findAll {it.id = %d}.status", orderId), hasItems("registred"));
    }
}
