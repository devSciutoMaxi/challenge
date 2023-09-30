package com.tenpo.challenge.api.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tenpo.challenge.api.models.AddRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class OperationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private ObjectMapper Obj;

    private AddRequest request;


    @Test
    public void addLogOk() throws Exception {
        Obj = new ObjectMapper();
        request = new AddRequest(1, 2);
        for (int i = 0; i < 3; i++) {
            mockMvc.perform(MockMvcRequestBuilders.post("/add")
                            .content(Obj.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk());
        }
    }

    @Test
    public void addLogWithTooManyRequest() throws Exception {
        Obj = new ObjectMapper();
        request = new AddRequest(1, 2);
        mockMvc.perform(MockMvcRequestBuilders.post("/add")
                        .content(Obj.writeValueAsString(request))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());

    }

    @Test
    public void getLogs() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/logs")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
