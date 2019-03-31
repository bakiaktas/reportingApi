package com.baktas.reportingapi.services;

import com.baktas.reportingapi.models.LoginResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TokenHandlerService {

    @Value("${sandbox.client.email}")
    private String reportingClientEmail;

    @Value("${sandbox.client.password}")
    private String reportingClientPassword;

    private LoginService _loginService;

    public TokenHandlerService(LoginService loginService) {
        _loginService = loginService;
    }

    private Optional<String> token = Optional.ofNullable(null);

    public String getServiceToken() {
        return  token.orElseGet(()-> {
            LoginResponse response = _loginService.login(reportingClientEmail, reportingClientPassword).orElseThrow(() -> new RuntimeException(""));
            token = Optional.ofNullable(response.getToken());
            return token.get();
        });
    }

    public void invalidateToken() {
        this.token =  Optional.ofNullable(null);
    }
}