package com.demo.onlineshopping.web.model;

import lombok.*;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ReviewDto extends BaseDto {

    private String title;

    private String description;

    private String clientName;

    private Long orderId;

    @Builder
    public ReviewDto(Long id, OffsetDateTime createdTime, OffsetDateTime lastModifiedTime,
                     String title, String description, String clientName, Long orderId) {
        super(id, createdTime, lastModifiedTime);
        this.title = title;
        this.description = description;
        this.clientName = clientName;
        this.orderId = orderId;
    }
}
