package com.vti.halloween.service;

import com.vti.halloween.exception.ApplicationException;
import com.vti.halloween.model.CardEntity;
import org.springframework.web.multipart.MultipartFile;

public interface GameService {
    CardEntity play() throws ApplicationException;
    String reset(String account) throws ApplicationException;
    String resetAll();
    String cartSeeding(MultipartFile file);
}
