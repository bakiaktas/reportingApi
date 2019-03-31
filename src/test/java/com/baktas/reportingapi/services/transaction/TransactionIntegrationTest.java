package com.baktas.reportingapi.services.transaction;

import com.baktas.reportingapi.models.*;
import com.baktas.reportingapi.entities.JsonConverter;

import com.baktas.reportingapi.services.TokenHandlerService;
import com.baktas.reportingapi.services.TransactionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionIntegrationTest {

    @Autowired
    TransactionService service;

    @Autowired
    TokenHandlerService tokenHandlerService;

    private String _token;

    @Before
    public void setUp() throws Exception {
        _token = tokenHandlerService.getServiceToken();
    }

    @Test
    public void givenStartAndEndDateWhenTransactionThenApproved(){
        Optional<TransactionReportResponse> transactionReportResponse = service.reportTransaction(new TransactionReportRequest("2015-07-01","2019-03-01"), _token);
        assertThat(transactionReportResponse).isNotNull().matches(x->"APPROVED".equals(x.get().getStatus()) && x.get().getResponse().size()>0 );
    }

    @Test
    public void givenTransactionIdWhenTransactionThenValidResponse() throws IOException {
        TransactionResponse transactionResponse = JsonConverter.convertToObject("transactionResponse.json", TransactionResponse.class);
        Optional<TransactionResponse> resp = service.getTransaction(new TransactionRequest(transactionResponse.getTransaction().getMerchant().getTransactionId()), _token);
        assertThat(resp.get()).isEqualToComparingFieldByFieldRecursively(transactionResponse);
    }
}