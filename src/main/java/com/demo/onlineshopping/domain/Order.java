package com.demo.onlineshopping.domain;

import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Order_Placement")
public class Order extends BaseEntity{

    private Long buyerId;

    private BigDecimal totalAmount;

    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private Set<Review> reviews = new HashSet<>();

    @Builder
    public Order(Long id, Timestamp createdTime, Timestamp lastModifiedTime, Long buyerId, BigDecimal totalAmount,
                 OrderStatus orderStatus, Set<Review> reviews) {
        super(id, createdTime, lastModifiedTime);
        this.buyerId = buyerId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.reviews = reviews;
    }

    public void setReviews(Set<Review> reviews) {
        for(Review review:reviews)
            review.setOrder(this);
        this.reviews = reviews;
    }

}
