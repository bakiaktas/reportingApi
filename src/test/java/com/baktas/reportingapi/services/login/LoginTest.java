package com.baktas.reportingapi.services.login;

import com.baktas.reportingapi.models.LoginRequest;
import com.baktas.reportingapi.models.LoginResponse;
import com.baktas.reportingapi.services.LoginService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginTest {

    @Autowired
    private LoginService loginService;

    @MockBean
    private RestTemplate template;

    @Test
    public void givenUserNameAndPasswordWhenLoginThenApproved(){
        when(template.postForEntity(any(String.class), new LoginRequest(any(),any()), LoginResponse.class)).thenReturn(new ResponseEntity(new LoginResponse("123","APPROVED"),HttpStatus.OK));
        Optional<LoginResponse> loginResponse = loginService.login("","");
        assertThat(loginResponse).isNotNull().matches(x-> x.get().getStatus().equals("APPROVED"));
    }

    @Test
    public void givenUserNameAndPasswordWhenLoginThenDeclined(){
        when(template.postForEntity(any(String.class), new LoginRequest(any(),any()), LoginResponse.class)).thenReturn(new ResponseEntity(new LoginResponse("123","DECLINED"),HttpStatus.OK));
        Optional<LoginResponse> loginResponse = loginService.login("","");
        assertThat(loginResponse).isNotNull().matches(x-> x.get().getStatus().equals("DECLINED"));
    }
}