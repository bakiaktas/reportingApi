package com.baktas.reportingapi.controllers;

import com.baktas.reportingapi.models.ClientInfoRequest;
import com.baktas.reportingapi.models.ClientInfoResponse;
import com.baktas.reportingapi.exceptions.RestApiException;
import com.baktas.reportingapi.services.ClientInformationService;
import com.baktas.reportingapi.services.TokenHandlerService;
import com.baktas.reportingapi.entities.IResponseEntity;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientInformationController {

    public static final String DECLINED = "DECLINED";

    private ClientInformationService _clientInformationService;
    private TokenHandlerService _tokenHandlerService;

    public ClientInformationController(ClientInformationService clientInformationService, TokenHandlerService tokenHandlerService) {
        _clientInformationService = clientInformationService;
        _tokenHandlerService = tokenHandlerService;
    }

    @GetMapping("/customer-infos/{transactionId}")
    public ResponseEntity<ClientInfoResponse> getClientInformation(@PathVariable String transactionId) {
        String serviceToken = _tokenHandlerService.getServiceToken();
        Optional<ClientInfoResponse> clientInfo = _clientInformationService.getClientInfo(new ClientInfoRequest(transactionId), serviceToken);
        clientInfo.ifPresent(resp -> {
            if(DECLINED.equals(resp.getStatus()))
                throw new RestApiException(clientInfo.get().getMessage());
        });
        return IResponseEntity.getResponseEntity(clientInfo);
    }
}