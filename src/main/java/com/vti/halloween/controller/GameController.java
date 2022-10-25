package com.vti.halloween.controller;

import com.vti.halloween.base.BaseController;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
public class GameController extends BaseController<Object> {

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @GetMapping("/test")
    public String test() {
        return "test";
    }
}
