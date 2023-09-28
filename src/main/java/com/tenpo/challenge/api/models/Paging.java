package com.tenpo.challenge.api.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class Paging {

    private Long offset;
    private Long limit;
    private Long total;
}
