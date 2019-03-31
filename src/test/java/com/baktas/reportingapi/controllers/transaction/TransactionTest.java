package com.baktas.reportingapi.controllers.transaction;

import com.baktas.reportingapi.ReportingapiApplication;
import com.baktas.reportingapi.controllers.TransactionController;
import com.baktas.reportingapi.models.*;
import com.baktas.reportingapi.exceptions.RestApiExceptionHandler;
import com.baktas.reportingapi.services.TokenHandlerService;
import com.baktas.reportingapi.services.TransactionService;
import com.baktas.reportingapi.entities.JsonConverter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.MockitoAnnotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportingapiApplication.class)
public class TransactionTest {

    private MockMvc _mockMvc;

    @MockBean
    TokenHandlerService tokenHandlerService;

    @MockBean
    TransactionService transactionService;

    @Autowired
    TransactionController controller;

    @Autowired
    RestApiExceptionHandler restExceptionHandler;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this._mockMvc = standaloneSetup(this.controller)
                .setControllerAdvice(restExceptionHandler).build();
    }

    @Test
    public void givenTransactionIdWithTokenWhenTransactionThenNotFound() throws Exception {
        TransactionResponse transactionResponse = new TransactionResponse();
        transactionResponse.setStatus("DECLINED");

        when(tokenHandlerService.getServiceToken()).thenReturn("Token");
        when(transactionService.getTransaction(any(TransactionRequest.class),any(String.class))).thenReturn(Optional.ofNullable(transactionResponse));

        _mockMvc.perform(get("/api/transactions/{transactionId}", "1010992-1539329625-1293" ))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }

    @Test
    public void givenStartAndEndDateWithTokenWhenTransactionThenApproved() throws Exception {
        TransactionReportResponse transactionReportResponse = JsonConverter.convertToObject("transactionReportResponse.json", TransactionReportResponse.class);
        when(tokenHandlerService.getServiceToken()).thenReturn("Token");
        when(transactionService.reportTransaction(any(TransactionReportRequest.class),any(String.class))).thenReturn(Optional.ofNullable(transactionReportResponse));
        _mockMvc.perform(get("/api/transaction-reports?start={startDate}&end={endDate}","2015-07-01","2019-10-01" ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.status").value("APPROVED"));
    }

    @Test
    public void givenTransactionIdWithTokenWhenTransactionThenValidResponse() throws Exception {
        TransactionResponse transactionResponse = JsonConverter.convertToObject("transactionResponse.json", TransactionResponse.class);

        when(tokenHandlerService.getServiceToken()).thenReturn("Token");
        when(transactionService.getTransaction(any(TransactionRequest.class),any(String.class))).thenReturn(Optional.ofNullable(transactionResponse));

        MerchantTransaction merchant = transactionResponse.getTransaction().getMerchant();
        _mockMvc.perform(get("/api/transactions/{transactionId}", "1010992-1539329625-1293" ))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.transaction.merchant.id").value(merchant.getId().intValue()))
                .andExpect(jsonPath("$.transaction.merchant.referenceNo").value(merchant.getReferenceNo()))
                .andExpect(jsonPath("$.transaction.merchant.status").value(merchant.getStatus()))
                .andExpect(jsonPath("$.transaction.merchant.operation").value(merchant.getOperation()))
                .andExpect(jsonPath("$.transaction.merchant.type").value(merchant.getType()))
                .andExpect(jsonPath("$.transaction.merchant.transactionId").value(merchant.getTransactionId()));
    }
}