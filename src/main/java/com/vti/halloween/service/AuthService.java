package com.vti.halloween.service;

import com.google.firebase.auth.FirebaseAuthException;
import com.vti.halloween.dto.LoginResponse;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.Principal;

public interface AuthService {
    LoginResponse login(String token) throws GeneralSecurityException, IOException, FirebaseAuthException;
}
