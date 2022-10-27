package com.vti.halloween.service;

import com.vti.halloween.exception.ApplicationException;
import org.springframework.web.multipart.MultipartFile;

public interface GameService {
    Integer play() throws ApplicationException;
    String reset(String account) throws ApplicationException;

    String cartSeeding(MultipartFile file);
}
