package qa.utils;

import static com.jayway.restassured.RestAssured.*;

import com.jayway.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import qa.data.OrderData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
public class RestUtility {

    public Response addTask(OrderData orderData) {
        Map<String,String> order = new HashMap<>();
        order.put("string", orderData.getMessage());
        order.put("startTime", orderData.getCreateDate());

        return given()
                .contentType("application/json")
                .body(order)
                .when().post("tasks");
    }

    public Response getTaskStatus() {
        String request = String.format("/tasks/status");

        return given().when().get(request);
    }

    public Response getTasksHistory() {
        return given().when().get("tasks/history");
    }

}
