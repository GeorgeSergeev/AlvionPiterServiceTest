package ru.alvioneurope.sample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.alvioneurope.sample.dto.CreateOrderRequest;
import ru.alvioneurope.sample.dto.CreateOrderResponse;
import ru.alvioneurope.sample.dto.ErrorResponse;
import ru.alvioneurope.sample.exception.ApiException;
import ru.alvioneurope.sample.model.Order;
import ru.alvioneurope.sample.service.OrderService;
import ru.alvioneurope.sample.validator.CreateOrderRequestValidator;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CreateOrderRequestValidator createOrderRequestValidator;

    @RequestMapping(value = "/tasks/history", method = GET)
    public List<Order> getOrderHistory() {
        return orderService.getOrderHistory();
    }

    @RequestMapping(value = "/tasks/status", method = GET)
    public List<Order> getOrderStatus() {
        return orderService.getOrdersStatus();
    }

    @RequestMapping(value = "/tasks", method = POST)
    public CreateOrderResponse storeOrder(@RequestBody CreateOrderRequest orderRequest, final BindingResult errors) {
        createOrderRequestValidator.validate(orderRequest, errors);
        if(errors.hasErrors()) {
            throw new ApiException("date isn't valid");
        }
        return orderService.storeOrder(orderRequest);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setErrorCode((long) HttpStatus.BAD_REQUEST.value());
        error.setMessage(ex.getMessage());
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

}
