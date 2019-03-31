package com.baktas.reportingapi.services.clientinfo;

import com.baktas.reportingapi.models.ClientInfoRequest;
import com.baktas.reportingapi.models.ClientInfoResponse;

import com.baktas.reportingapi.entities.JsonConverter;

import com.baktas.reportingapi.services.ClientInformationService;
import com.baktas.reportingapi.services.TokenHandlerService;

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
public class ClientInfoIntegrationTest {

    @Autowired
    private ClientInformationService clientInformationService;

    @Autowired
    TokenHandlerService tokenHandlerService;

    private String _token;

    @Before
    public void setUp() throws Exception {
        _token = tokenHandlerService.getServiceToken();
    }

    @Test
    public void givenTransactionIdWhenClientInfoThenValidResponse() throws IOException {
        ClientInfoResponse clientInfoResponse = JsonConverter.convertToObject("clientInfoResponse.json", ClientInfoResponse.class);
        Optional<ClientInfoResponse> clientInfo = clientInformationService.getClientInfo(new ClientInfoRequest("1010992-1539329625-1293"), _token);
        assertThat(clientInfo.get()).isEqualToComparingFieldByFieldRecursively(clientInfoResponse);
    }
}