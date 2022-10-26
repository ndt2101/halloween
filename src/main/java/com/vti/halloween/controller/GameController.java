package com.vti.halloween.controller;

import com.vti.halloween.base.BaseController;
import com.vti.halloween.exception.ApplicationException;
import com.vti.halloween.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/game")
public class GameController extends BaseController<Object> {

    @Autowired
    private GameService gameService;

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @GetMapping("/play")
    public ResponseEntity<?> play() throws ApplicationException {
        return this.successfulResponse(gameService.play());
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @DeleteMapping("/reset/{account}")
    public ResponseEntity<?> reset(@PathVariable("account") String account) throws ApplicationException {
        return this.successfulResponse(gameService.reset(account));
    }
}
