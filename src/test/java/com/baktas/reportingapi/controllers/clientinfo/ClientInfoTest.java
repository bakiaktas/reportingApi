package com.baktas.reportingapi.controllers.clientinfo;

import com.baktas.reportingapi.ReportingapiApplication;
import com.baktas.reportingapi.controllers.ClientInformationController;
import com.baktas.reportingapi.models.CustomerInfo;
import com.baktas.reportingapi.models.ClientInfoRequest;
import com.baktas.reportingapi.models.ClientInfoResponse;
import com.baktas.reportingapi.exceptions.RestApiExceptionHandler;
import com.baktas.reportingapi.services.ClientInformationService;
import com.baktas.reportingapi.services.TokenHandlerService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReportingapiApplication.class)
public class ClientInfoTest {

    private MockMvc restCustomerInfoMockMvc;

    @MockBean
    TokenHandlerService tokenHandlerService;

    @MockBean
    ClientInformationService clientInformationService;

    @Autowired
    ClientInformationController controller;

    @Autowired
    RestApiExceptionHandler restExceptionHandler;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.restCustomerInfoMockMvc = standaloneSetup(this.controller)
                .setControllerAdvice(restExceptionHandler).build();
    }

    @Test
    public void givenTransactionIdWithTokenWhenCustomerInfoThenNotFound() throws Exception {
        ClientInfoResponse mockResp = new ClientInfoResponse();
        mockResp.setStatus("DECLINED");
        when(tokenHandlerService.getServiceToken()).thenReturn("Token");
        when(clientInformationService.getClientInfo(any(ClientInfoRequest.class),anyString())).thenReturn(java.util.Optional.ofNullable(mockResp));
        restCustomerInfoMockMvc.perform(get("/api/customer-infos/{transactionId}", Long.MAX_VALUE))
                .andExpect(status().isNotFound()).
                andExpect(jsonPath("$.status").value("NOT_FOUND"));
    }

    @Test
    public void givenTransactionIdWithTokenWhenCustomerInfoThenValidResponse() throws Exception {
        ClientInfoResponse clientInfoResponse = JsonConverter.convertToObject("clientInfoResponse.json", ClientInfoResponse.class);
        when(tokenHandlerService.getServiceToken()).thenReturn("Token");
        when(clientInformationService.getClientInfo(any(ClientInfoRequest.class),anyString())).thenReturn(java.util.Optional.ofNullable(clientInfoResponse));
        CustomerInfo info = clientInfoResponse.getCustomerInfo();
        restCustomerInfoMockMvc.perform(get("/api/customer-infos/{transactionId}", clientInfoResponse.getTransaction().getMerchant().getTransactionId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andExpect(jsonPath("$.customerInfo.id").value(info.getId().intValue()))
                .andExpect(jsonPath("$.customerInfo.number").value(info.getNumber()))
                .andExpect(jsonPath("$.customerInfo.expiryMonth").value(info.getExpiryMonth()))
                .andExpect(jsonPath("$.customerInfo.expiryYear").value(info.getExpiryYear()))
                .andExpect(jsonPath("$.customerInfo.issueNumber").value(info.getIssueNumber()))
                .andExpect(jsonPath("$.customerInfo.email").value(info.getEmail().toString()))
                .andExpect(jsonPath("$.customerInfo.birthday").value(info.getBirthday().toString()))
                .andExpect(jsonPath("$.customerInfo.gender").value(info.getGender()))
                .andExpect(jsonPath("$.customerInfo.billingTitle").value(info.getBillingTitle()))
                .andExpect(jsonPath("$.customerInfo.billingFirstName").value(info.getBillingFirstName()))
                .andExpect(jsonPath("$.customerInfo.billingLastName").value(info.getBillingLastName()))
                .andExpect(jsonPath("$.customerInfo.billingCompany").value(info.getBillingCompany()))
                .andExpect(jsonPath("$.customerInfo.billingAddress1").value(info.getBillingAddress1()))
                .andExpect(jsonPath("$.customerInfo.billingAddress2").value(info.getBillingAddress2()))
                .andExpect(jsonPath("$.customerInfo.billingCity").value(info.getBillingCity()));
    }
}