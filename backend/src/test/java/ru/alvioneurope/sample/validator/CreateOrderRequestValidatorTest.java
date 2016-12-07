package ru.alvioneurope.sample.validator;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import ru.alvioneurope.sample.Application;
import ru.alvioneurope.sample.dto.CreateOrderRequest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by lkhruschev on 07.12.2016.
 * lkhruschev@alvioneurope.ru
 * Skype: levasfx
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(Application.class)
public class CreateOrderRequestValidatorTest {

    @Autowired
    private CreateOrderRequestValidator createOrderRequestValidator;

    @Test
    public void testValidate() throws Exception {
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        orderRequest.setString("aasdasdf");
        orderRequest.setStartTime("07-12-16 10:22:34 +03:00");
        final BindingResult errors = new BeanPropertyBindingResult(orderRequest, "orderResult");
        createOrderRequestValidator.validate(orderRequest, errors);
        assertFalse(errors.hasErrors());
    }

    @Test
    public void testNotValidateString() throws Exception {
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        orderRequest.setString("aasdaaaasdasdddasfghethndsdf");
        orderRequest.setStartTime("07-12-16 10:22:34 +03:00");
        final BindingResult errors = new BeanPropertyBindingResult(orderRequest, "orderResult");
        createOrderRequestValidator.validate(orderRequest, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testNotValidateStartTime() throws Exception {
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        orderRequest.setString("aasdaaaaf");
        orderRequest.setStartTime("07-12-16 10:22:34 1+03:00");
        final BindingResult errors = new BeanPropertyBindingResult(orderRequest, "orderResult");
        createOrderRequestValidator.validate(orderRequest, errors);
        assertTrue(errors.hasErrors());
    }

    @Test
    public void testNotValidateAll() throws Exception {
        CreateOrderRequest orderRequest = new CreateOrderRequest();
        orderRequest.setString("aahjd8nw0nsonsd0vsdaaaaf");
        orderRequest.setStartTime("07-12-16T10:22:34 +03:00a");
        final BindingResult errors = new BeanPropertyBindingResult(orderRequest, "orderResult");
        createOrderRequestValidator.validate(orderRequest, errors);
        assertTrue(errors.getErrorCount() == 2);
    }

}