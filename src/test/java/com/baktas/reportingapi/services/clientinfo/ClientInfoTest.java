package com.baktas.reportingapi.services.clientinfo;

import com.baktas.reportingapi.models.ClientInfoRequest;
import com.baktas.reportingapi.models.ClientInfoResponse;
import com.baktas.reportingapi.entities.JsonConverter;
import com.baktas.reportingapi.services.ClientInformationService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
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
public class ClientInfoTest {

    @MockBean
    RestTemplate restTemplate;

    @Autowired
    private ClientInformationService clientInformationService;

    @Test
    public void givenNotTransactionIdWhenClientInfoThenValidResponse() throws IOException {
        ClientInfoResponse clientInfoResponse = JsonConverter.convertToObject("clientInfoResponse.json", ClientInfoResponse.class);
        when(restTemplate.postForObject(anyString(), any(HttpEntity.class),any())).thenReturn(clientInfoResponse);
        Optional<ClientInfoResponse> clientInfo = clientInformationService.getClientInfo(new ClientInfoRequest(""), "");
        assertThat(clientInfo.get()).isEqualToComparingFieldByFieldRecursively(clientInfoResponse);
    }
}