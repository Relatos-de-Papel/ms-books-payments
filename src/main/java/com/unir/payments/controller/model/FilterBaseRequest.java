package com.unir.payments.controller.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilterBaseRequest {

    private Integer page;
    private Integer pageSize;
}
