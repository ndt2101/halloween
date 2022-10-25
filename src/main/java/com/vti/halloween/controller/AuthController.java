package com.vti.halloween.controller;

import com.vti.halloween.dto.oauthresponse.OAuthResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(Principal model) {
        Authentication authentication = SecurityContextHolder.getContext()
                .getAuthentication();

        Object principal = authentication.getPrincipal();
        if(principal instanceof DefaultOidcUser){
            DefaultOidcUser oAuth2AuthenticationToken = (DefaultOidcUser)principal;
            //By default its DefaultOAuth2User.
            String name = oAuth2AuthenticationToken.getAttribute("name");
            String mail = oAuth2AuthenticationToken.getAttribute("email");
            return mail;
        } else
            return null;
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

}
