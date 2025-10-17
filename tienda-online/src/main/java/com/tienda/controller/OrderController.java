package com.tienda.controller;

import com.tienda.model.Order;
import com.tienda.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * REST CONTROLLER
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * CREATE A NEW ORDER
     * @param order ORDER DATA
     * @return CREATE DATA
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@Valid @RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return new ResponseEntity<>(createdOrder, HttpStatus.CREATED);
    }

    /**
     *GET ALL OORDERS.
     * @return ORDER LIST COMPLETE
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    /**
     * GET ORDER BY ID
     * @param id 
     * @return THE ORDER'S ID OR 404 IF DON'T EXIST
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable Long id) {
        Optional<Order> order = orderService.getOrderById(id);

        if (order.isPresent()) {
            return new ResponseEntity<>(order.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * UPDATE
     * @param id identificador
     * @param orderDetails DATA TO UPDATE
     * @return UPDATE DATA OR 404 IF DON'T EXIST
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @Valid @RequestBody Order orderDetails) {
        Optional<Order> updatedOrder = orderService.updateOrder(id, orderDetails);

        return updatedOrder.map(order -> new ResponseEntity<>(order, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * DELETE ORDER BY ID
     * @param id identificador
     * @return 204IF EXISTS, 404 IF DON'T
     */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        boolean deleted = orderService.deleteOrder(id);

        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}