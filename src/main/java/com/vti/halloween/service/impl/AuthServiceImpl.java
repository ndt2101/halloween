package com.vti.halloween.service.impl;


import com.vti.halloween.dto.LoginResponse;
import com.vti.halloween.service.AuthService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collection;
import java.util.Collections;


@Service
public class AuthServiceImpl implements AuthService {

    @Value("${auth.client_id}")
    private String CLIENT_ID;

    @Override
    public LoginResponse login(String token) throws GeneralSecurityException, IOException {
//        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(new NetHttpTransport(), new GsonFactory())
//                .setAudience(Collections.singletonList(CLIENT_ID))
//                .build();
//
//        GoogleIdToken idToken = verifier.verify(token);
//        if (idToken != null) {
//            Payload payload = idToken.getPayload();
//            String userId = payload.getSubject();
//            System.out.println("User ID: " + userId);
//
//            String email = payload.getEmail();
//            boolean emailVerified = payload.getEmailVerified();
//            String name = (String) payload.get("name");
//            String pictureUrl = (String) payload.get("picture");
//            String locale = (String) payload.get("locale");
//            String familyName = (String) payload.get("family_name");
//            String givenName = (String) payload.get("given_name");
//
////            ==========================================================================================================
//
//            String username = email.replace("@vti.com.vn", "");
//
            return new LoginResponse("username", "accessToken"); // TODO: access token
//        } else {
//            throw new InternalAuthenticationServiceException("invalid email token!");
//        }



    }

}
