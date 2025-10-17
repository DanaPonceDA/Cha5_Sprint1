package com.tienda.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

/**
 * Dana Elizabeth Ponce Del Angel
 * Entity representing an order in the online store.
 *
 * Contains customer information, product details, quantity, total price,
 * status, and creation date.
 *
 * @version 1.0.0
 * <p>
 * 1.0.0 - Initial version with all fields, JPA mappings, and validation constraints.
 * </p>
 */
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    private String customerName;

    @Column(nullable = false)
    @NotBlank(message = "El email del cliente no puede estar vacío")
    @Email(message = "El email debe tener un formato válido")
    private String customerEmail;

    @Column(nullable = false)
    @NotBlank(message = "La descripción del producto no puede estar vacía")
    private String productDescription;

    @Column(nullable = false)
    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer quantity;

    @Column(nullable = false)
    @NotNull(message = "El precio total es obligatorio")
    @PositiveOrZero(message = "El precio total debe ser 0 o mayor")
    private Double totalPrice;

    @Column(nullable = false)
    @NotBlank(message = "El estado no puede estar vacío")
    private String status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    /**
     * Default constructor. Initializes createdAt and status defaults.
     * @since 1.0.0
     */
    public Order() {
        this.createdAt = LocalDateTime.now();
        this.status = "PENDING";
    }

    // Getters and Setters

    /**
     * Gets the order identifier.
     * @return order ID
     * @since 1.0.0
     */
    public Long getId() {
        return id;
    }

    /***
     * Sets the order identifier.
     * @param id identifier
     * @since 1.0.0
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the customer name.
     * @return customer name
     * @since 1.0.0
     */
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}