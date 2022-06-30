package com.demo.onlineshopping.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BaseDto {

    private Long id;

    private OffsetDateTime createdTime;

    private OffsetDateTime lastModifiedTime;

}
