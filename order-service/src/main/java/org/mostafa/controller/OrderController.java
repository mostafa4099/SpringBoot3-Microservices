package org.mostafa.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mostafa.model.OrderReqModel;
import org.mostafa.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Md. Golam Mostafa | mostafa.sna@gmail.com
 * @CreationDate 8/11/2024 3:55 PM
 */
@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;
    private Logger logger = LoggerFactory.getLogger(OrderController.class);;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String placeOrder(@RequestBody OrderReqModel orderRequest, @RequestHeader("loggedUsername") String loggedUsername) {
        logger.info("Logged Username: "+loggedUsername);
        orderService.placeOrder(orderRequest);
        return "Order Placed Successfully";
    }
}
