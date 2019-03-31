package com.baktas.reportingapi.services.transaction;

import com.baktas.reportingapi.models.*;
import com.baktas.reportingapi.entities.JsonConverter;
import com.baktas.reportingapi.services.LoginService;
import com.baktas.reportingapi.services.TransactionService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.mockito.Matchers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TransactionTest {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private TransactionService service;

    @Autowired
    LoginService loginService;

    @Test
    public void givenStartAndEndDateWhenTransactionThenValidResponse() throws IOException {
        TransactionReportResponse transactionReportResponse = JsonConverter.convertToObject("transactionReportResponse.json", TransactionReportResponse.class);
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class),Matchers.<Class<TransactionReportResponse>>any())).thenReturn(new ResponseEntity<>(transactionReportResponse,HttpStatus.OK));
        Optional<TransactionReportResponse> resp = service.reportTransaction(new TransactionReportRequest("2015-07-01","2019-03-01"),null);
        assertThat(resp).isNotNull().matches(x->"APPROVED".equals(x.get().getStatus())
                && x.get().getResponse().size()== transactionReportResponse.getResponse().size() );
        assertThat(resp.get()).isEqualToComparingFieldByFieldRecursively(transactionReportResponse);
    }

    @Test
    public void givenTransactionIdWhenTransactionThenValidResponse() throws IOException {
        TransactionResponse transactionResponse = JsonConverter.convertToObject("transactionResponse.json", TransactionResponse.class);
        when(restTemplate.postForEntity(anyString(), any(HttpEntity.class),Matchers.<Class<TransactionResponse>>any())).thenReturn(new ResponseEntity<>(transactionResponse,HttpStatus.OK));
        Optional<TransactionResponse> resp = service.getTransaction(new TransactionRequest("1010992-1539329625-1293"),null);
        assertThat(resp.get()).isEqualToComparingFieldByFieldRecursively(transactionResponse);
    }
}