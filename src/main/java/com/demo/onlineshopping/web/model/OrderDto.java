package com.demo.onlineshopping.web.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class OrderDto extends BaseDto {

    private Long buyerId;

    @JsonFormat(shape= JsonFormat.Shape.STRING)
    private BigDecimal totalAmount;

    private OrderStatusDto orderStatus;

    private Set<ReviewDto> reviews;

    @Builder
    public OrderDto(Long id, OffsetDateTime createdTime, OffsetDateTime lastModifiedTime, Long buyerId,
                    BigDecimal totalAmount, OrderStatusDto orderStatus, Set<ReviewDto> reviews) {
        super(id, createdTime, lastModifiedTime);
        this.buyerId = buyerId;
        this.totalAmount = totalAmount;
        this.orderStatus = orderStatus;
        this.reviews = reviews;
    }
}
