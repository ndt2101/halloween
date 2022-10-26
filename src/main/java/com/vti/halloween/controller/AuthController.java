package com.vti.halloween.controller;

import com.google.firebase.auth.FirebaseAuthException;
import com.vti.halloween.base.BaseController;
import com.vti.halloween.dto.LoginPayload;
import com.vti.halloween.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.security.GeneralSecurityException;


@RestController
@RequestMapping("/api/auth")
public class AuthController extends BaseController<Object> {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginPayload token) throws GeneralSecurityException, IOException, FirebaseAuthException {
        return this.successfulResponse(authService.login(token.getToken()));
    }

}
