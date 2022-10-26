package com.vti.halloween.service.impl;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.vti.halloween.dto.LoginResponse;
import com.vti.halloween.exception.NotFoundException;
import com.vti.halloween.model.UserEntity;
import com.vti.halloween.repository.UserRepository;
import com.vti.halloween.service.AuthService;
import com.vti.halloween.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.security.Principal;
import java.util.Collection;
import java.util.Collections;


@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    @Value("${auth.client_id}")
    private String CLIENT_ID;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;


    @Override
    public LoginResponse login(String token) throws FirebaseAuthException {
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
//                .setAudience(Collections.singletonList(CLIENT_ID))
//                .build();
//
//        GoogleIdToken idToken = verifier.verify(token);

        FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);

        if (decodedToken != null) {
//            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = decodedToken.getEmail();

            String username = email.replace("@vti.com.vn", "");
            System.out.println("User name: " + username);

            UserEntity userEntity = userRepository.findByAccount(username)
                        .orElseThrow(() -> new NotFoundException("User not found!"));
                String jwt = jwtUtils.generateToken(username);
                return new LoginResponse(userEntity.getFullName(), jwt);
        } else {
            throw new InternalAuthenticationServiceException("You must use vti organization email!");
        }
    }
}
