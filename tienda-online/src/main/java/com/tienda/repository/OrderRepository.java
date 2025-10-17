package com.tienda.repository;

import com.tienda.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * Dana ELizabeth Ponce Del Angel
 * Repository interface for managing Order database operations.
 *
 * It extends JpaRepository, providing standard CRUD (Create, Read, Update, Delete) 
 * functionality for the Order entity with a Long primary key.
 *
 * @version 1.0.0
 * <p>
 * 1.0.0 - Initial version extending JpaRepository.
 * </p>
 */

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}