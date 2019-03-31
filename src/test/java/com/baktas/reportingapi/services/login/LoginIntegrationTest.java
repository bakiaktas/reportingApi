package com.baktas.reportingapi.services.login;

import com.baktas.reportingapi.models.LoginResponse;
import com.baktas.reportingapi.services.LoginService;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class LoginIntegrationTest {

    @Autowired
    private LoginService loginService;

    @Test
    public void givenUserNameAndPasswordWhenLoginThenApproved(){
        Optional<LoginResponse> loginResponse = loginService.login("demo@bumin.com.tr","cjaiU8CV");
        assertThat(loginResponse).isNotNull().matches(x-> x.get().getStatus().equals("APPROVED"));
    }
}