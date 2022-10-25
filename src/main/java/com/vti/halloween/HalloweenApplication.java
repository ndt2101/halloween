package com.vti.halloween;

import com.vti.halloween.model.UserEntity;
import com.vti.halloween.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class HalloweenApplication {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(HalloweenApplication.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner(){
        return args -> {
            List<UserEntity> dummy = new ArrayList<>();
            dummy.add(new UserEntity("tuan.nguyendinh"));
            userRepository.saveAll(dummy);
        };
    }

}
