package com.tenpo.challenge.api.models;

import com.tenpo.challenge.api.dto.Log;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@AllArgsConstructor
@Setter
@Getter
public class LogsResponse {

    private Paging paging;
    private List<Log> results;

}
