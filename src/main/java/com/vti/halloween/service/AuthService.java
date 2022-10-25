package com.vti.halloween.service;

import com.vti.halloween.dto.LoginResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface AuthService {
    LoginResponse login(String token) throws GeneralSecurityException, IOException;
}
