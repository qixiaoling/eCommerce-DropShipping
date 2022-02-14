package com.nl.ecommerce.model;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;
import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
public class Cart_Composite_ID implements Serializable {
    @Column
    private Long customerId;
    @Column
    private Long productId;

    public Cart_Composite_ID() {
    }

    public Cart_Composite_ID(Long customerId, Long productId) {
        this.customerId = customerId;
        this.productId = productId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
}
