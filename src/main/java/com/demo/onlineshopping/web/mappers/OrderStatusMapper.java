package com.demo.onlineshopping.web.mappers;

import com.demo.onlineshopping.domain.OrderStatus;
import com.demo.onlineshopping.web.model.OrderStatusDto;
import org.springframework.stereotype.Component;

@Component
public class OrderStatusMapper {
    public OrderStatus orderStatusDtoToOrderStatus(OrderStatusDto orderStatusDto){
        switch (orderStatusDto){
            case PAYOFF:
                return OrderStatus.PAYOFF;
            case SHIPPED:
                return OrderStatus.SHIPPED;
            case CANCELLED:
                return OrderStatus.CANCELLED;
            case CONFIRMED:
                return OrderStatus.CONFIRMED;
            default:
                throw new IllegalArgumentException("Status is not valid" + orderStatusDto.name());
        }
    }

    public OrderStatusDto orderStatusToOrderStatusDto(OrderStatus orderStatus){
        switch (orderStatus){
            case PAYOFF:
                return OrderStatusDto.PAYOFF;
            case SHIPPED:
                return OrderStatusDto.SHIPPED;
            case CANCELLED:
                return OrderStatusDto.CANCELLED;
            case CONFIRMED:
                return OrderStatusDto.CONFIRMED;
            default:
                throw new IllegalArgumentException("Status is not valid" + orderStatus.name());
        }
    }
}
