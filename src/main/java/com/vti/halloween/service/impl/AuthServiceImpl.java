package com.vti.halloween.service.impl;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.oauth2.GoogleCredentials;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@Service
public class AuthServiceImpl implements AuthService {

//    @Value("${auth.client_id}")
    private String CLIENT_ID = "633008572773-v1ur6rp8c4vtl77olpdqu3rho4d2ui1p.apps.googleusercontent.com";

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public LoginResponse login(String token) throws GeneralSecurityException, IOException, FirebaseAuthException {
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
//                .setAudience(Collections.singletonList(CLIENT_ID))
//                .build();
//
//        GoogleIdToken idToken = verifier.verify(token);

        if (idToken != null) {
//            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = idToken.getEmail();

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

//    @Override
//    public LoginResponse login(Principal model) throws GeneralSecurityException, IOException {
//        Authentication authentication = SecurityContextHolder.getContext()
//                .getAuthentication();
//        Object principal = authentication.getPrincipal();
//        if (principal instanceof DefaultOidcUser) {
//            DefaultOidcUser defaultOidcUser = (DefaultOidcUser) principal;
//            String mail = defaultOidcUser.getAttribute("email");
//            if (mail != null) {
//                String username = mail.replace("@vti.com.vn", "");
//                UserEntity userEntity = userRepository.findByAccount(username)
//                        .orElseThrow(() -> new NotFoundException("User not found!"));
//                String jwt = jwtUtils.generateToken(username);
//                return new LoginResponse(userEntity.getFullName(), jwt);
//            }
//
//            return new LoginResponse("username", "accessToken"); // TODO: access token
//        } else
//            return null;
//    }


}
