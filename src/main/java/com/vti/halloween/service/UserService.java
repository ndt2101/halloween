package com.vti.halloween.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    String seedData(MultipartFile file) throws IOException;
}
