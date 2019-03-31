package com.baktas.reportingapi.services;

import com.baktas.reportingapi.models.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static com.baktas.reportingapi.entities.HttpContext.getHttpEntity;

@Service
public class TransactionService {
    @Value("${sandbox.client.url}")
    private String reportingServerUrl;

    RestTemplate _restTemplate;

    public TransactionService(RestTemplate restTemplate) {
        _restTemplate = restTemplate;
    }

    public Optional<TransactionReportResponse> reportTransaction(TransactionReportRequest request, String token) {
        HttpEntity<?> httpEntity = getHttpEntity(request, token);
        ResponseEntity<TransactionReportResponse> response = _restTemplate.postForEntity(reportingServerUrl+"/v3/transactions/report", httpEntity, TransactionReportResponse.class);
        return Optional.ofNullable(response.getBody());
    }

    public Optional<TransactionResponse> getTransaction(TransactionRequest request, String token) {
        HttpEntity<?> httpEntity = getHttpEntity(request, token);
        ResponseEntity<TransactionResponse> response = _restTemplate.postForEntity(reportingServerUrl+"/v3/transaction", httpEntity, TransactionResponse.class);
        return Optional.ofNullable(response.getBody());
    }
}