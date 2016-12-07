package ru.alvioneurope.sample.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.alvioneurope.sample.dto.CreateOrderRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by lkhruschev on 07.12.2016.
 * lkhruschev@alvioneurope.ru
 * Skype: levasfx
 */
@Component
public class CreateOrderRequestValidator implements Validator {

    public boolean supports(Class clazz) {
        return CreateOrderRequest.class.equals(clazz);
    }

    public void validate(Object target, Errors errors) {

        CreateOrderRequest orderRequest = (CreateOrderRequest) target;
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH:mm:ss XXX");
        try {
            dateFormat.parse(orderRequest.getStartTime());
        } catch (ParseException e) {
            errors.rejectValue("startTime", "date isn't valid");
        }

        if(orderRequest.getString() == null || orderRequest.getString().length() < 1 || orderRequest.getString().length() > 20) {
            errors.rejectValue("string", "string isn't valid");
        }

    }
}
