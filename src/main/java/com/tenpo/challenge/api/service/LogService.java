package com.tenpo.challenge.api.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.api.dto.Log;
import com.tenpo.challenge.api.models.OperationResponse;
import com.tenpo.challenge.api.repository.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class LogService {

    @Autowired
    private LogRepository repository;

    public Log save(String response) {
        Log log = new Log();
        log.setResponse(response);
        return repository.save(log);
    }

    @Async
    public void saveAsync(OperationResponse response) throws JsonProcessingException {
        ObjectMapper obj = new ObjectMapper();
        save(obj.writeValueAsString(response));
    }
}