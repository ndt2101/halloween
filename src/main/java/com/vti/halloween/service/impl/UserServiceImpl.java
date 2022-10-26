package com.vti.halloween.service.impl;

import com.vti.halloween.model.UserEntity;
import com.vti.halloween.repository.UserRepository;
import com.vti.halloween.service.UserService;
import com.vti.halloween.utils.ExcelHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExcelHelper excelHelper;

    @Override
    public String seedData(MultipartFile file) {
        try {
            List<UserEntity> userEntities = excelHelper.excelToUsers(file.getInputStream());
            userRepository.saveAll(userEntities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
        return null;
    }
}
