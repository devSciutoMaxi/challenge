package com.tenpo.challenge.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.tenpo.challenge.api.dto.Log;
import com.tenpo.challenge.api.interceptors.RequestInterceptor;
import com.tenpo.challenge.api.models.AddRequest;
import com.tenpo.challenge.api.models.LogsResponse;
import com.tenpo.challenge.api.models.OperationResponse;
import com.tenpo.challenge.api.models.Paging;
import com.tenpo.challenge.api.repository.LogRepository;
import com.tenpo.challenge.api.service.LogService;
import com.tenpo.challenge.api.service.OperationService;
import com.tenpo.challenge.api.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static java.lang.Long.parseLong;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
public class OperationController {

    @Autowired
    private OperationService operationService;

    @Autowired
    private LogRepository repository;

    @Autowired
    private LogService logService;

    private final static Logger log = LoggerFactory.getLogger(OperationController.class);


    @PostMapping(value = "/add", produces = "application/json")
    @ResponseBody
    public ResponseEntity<OperationResponse> add(@RequestBody AddRequest request) throws JsonProcessingException {
        BigDecimal result = operationService.calculate(new BigDecimal(request.getNumberOne()), new BigDecimal(request.getNumberTwo()));
        OperationResponse response = new OperationResponse(request.getNumberOne(), request.getNumberTwo(), operationService.percentage, result);
        logService.saveAsync(response);
        return ResponseEntity.status(OK).body(response);
    }

    @RequestMapping(value = "/logs", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LogsResponse> logs(
            @RequestParam(defaultValue = Constants.OFFSET, required = false) String offset,
            @RequestParam(defaultValue = Constants.LIMIT, required = false) String limit) {

        Pageable pageRequest = PageRequest.of(Integer.parseInt(offset), Integer.parseInt(limit));
        Page<Log> result = repository.findAll(pageRequest);
        Paging paging = new Paging(parseLong(offset), parseLong(limit), result.getTotalElements());

        LogsResponse response = new LogsResponse(paging, result.getContent());
        return ResponseEntity.status(OK).body(response);
    }

}


