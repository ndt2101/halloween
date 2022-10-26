package com.vti.halloween.controller;

import com.vti.halloween.base.BaseController;
import com.vti.halloween.base.BaseEntity;
import com.vti.halloween.dto.SeedingRequest;
import com.vti.halloween.exception.NotFoundException;
import com.vti.halloween.repository.UserRepository;
import com.vti.halloween.service.UserService;
import com.vti.halloween.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
public class UserController extends BaseController<Object> {

    @Autowired
    private UserService userService;

    @PostMapping("/seed")
    public ResponseEntity<?> seedData(@ModelAttribute SeedingRequest seedingRequest) throws IOException {
        if (ExcelHelper.hasExcelFormat(seedingRequest.getFile())) {
//            try {
//                ;
//
////                message = "Uploaded the file successfully: " + seedingRequest.getFile().getOriginalFilename();
//
//            } catch (Exception e) {
//                message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//                return th;
//            }
            return this.successfulResponse(userService.seedData(seedingRequest.getFile()));
        } else {
            throw new NotFoundException("Could not upload the file:" + seedingRequest.getFile().getOriginalFilename());
        }
    }
}
