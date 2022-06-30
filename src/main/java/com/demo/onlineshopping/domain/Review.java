package com.demo.onlineshopping.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Review extends BaseEntity{

    private String title;

    private String description;

    private String clientName;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @Builder
    public Review(Long id, Timestamp createdTime, Timestamp lastModifiedTime, String title, String description,
                  String clientName, Order order) {
        super(id, createdTime, lastModifiedTime);
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.order = order;
    }
}
