package com.tienda.service;

import com.tienda.model.Order;
import com.tienda.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
/**
 * Dana Elizabeth Ponce Del Angel
 * Service containing the business logic for orders.
 *
 * Provides CRUD operations on the Order entity using JPA.
 *
 * @version 1.0.0
 * <p>
 * 1.0.0 - Initial version with all basic CRUD methods.
 * </p>
 */

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    /**
     * Creates a new order in the database.
     *
     * @param order the order information
     * @return the persisted order
     * @since 1.0.0
     */
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
/**
     * Retrieves all stored orders.
     *
     * @return list of orders
     * @since 1.0.0
     */
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
/**
     * Finds an order by its ID.
     *
     * @param id the order identifier
     * @return Optional containing the order if it exists
     * @since 1.0.0
     */
    
    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Updates an existing order (Full replacement using PUT semantics).
     *
     * @param id the identifier of the order to update
     * @param orderDetails data with which to update
     * @return Optional containing the updated order or empty if it does not exist
     * @since 1.0.0
     */
    public Optional<Order> updateOrder(Long id, Order orderDetails) {
        Optional<Order> existingOrder = orderRepository.findById(id);

        if (existingOrder.isPresent()) {
            Order order = existingOrder.get();
            order.setCustomerName(orderDetails.getCustomerName());
            order.setCustomerEmail(orderDetails.getCustomerEmail());
            order.setProductDescription(orderDetails.getProductDescription());
            order.setQuantity(orderDetails.getQuantity());
            order.setTotalPrice(orderDetails.getTotalPrice());
            order.setStatus(orderDetails.getStatus());

            return Optional.of(orderRepository.save(order));
        }

        return Optional.empty();
    }

    /**
     * Deletes an order by its ID.
     *
     * @param id the identifier
     * @return true if deleted, false if it did not exist
     * @since 1.0.0
     */
    public boolean deleteOrder(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        }
        return false;
    }
}