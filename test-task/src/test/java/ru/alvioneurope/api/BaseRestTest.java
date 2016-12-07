package ru.alvioneurope.api;

import com.jayway.restassured.RestAssured;
import org.junit.Before;

/**
 * @author Alexey Burnasov <aburnasov@alvioneurope.ru>
 */
public class BaseRestTest {
    @Before
    public void setup() {
        String port = System.getProperty("server.port");
        if (port == null) {
            RestAssured.port = Integer.valueOf(8080);
        }
        else{
            RestAssured.port = Integer.valueOf(port);
        }


        String basePath = System.getProperty("server.base");
        if(basePath==null){
            basePath = "/backend/api/";
        }
        RestAssured.basePath = basePath;

        String baseHost = System.getProperty("server.host");
        if(baseHost==null){
            baseHost = "http://192.168.1.47";
        }
        RestAssured.baseURI = baseHost;
    }
}
