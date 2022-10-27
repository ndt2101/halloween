package com.vti.halloween.controller;

import com.vti.halloween.base.BaseController;
import com.vti.halloween.dto.SeedingRequest;
import com.vti.halloween.exception.ApplicationException;
import com.vti.halloween.exception.NotFoundException;
import com.vti.halloween.service.GameService;
import com.vti.halloween.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

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

    @PostMapping("/seed")
    public ResponseEntity<?> seedData(@ModelAttribute SeedingRequest seedingRequest) throws IOException {
        if (ExcelHelper.hasExcelFormat(seedingRequest.getFile())) {
            return this.successfulResponse(gameService.cartSeeding(seedingRequest.getFile()));
        } else {
            throw new NotFoundException("Could not upload the file:" + seedingRequest.getFile().getOriginalFilename());
        }
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @DeleteMapping("/reset/{account}")
    public ResponseEntity<?> reset(@PathVariable("account") String account) throws ApplicationException {
        return this.successfulResponse(gameService.reset(account));
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @DeleteMapping("/resetAll")
    public ResponseEntity<?> resetAll() throws ApplicationException {
        return this.successfulResponse(gameService.resetAll());
    }
}
