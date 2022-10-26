package com.vti.halloween.service.impl;

import com.vti.halloween.exception.ApplicationException;
import com.vti.halloween.model.GameDataEntity;
import com.vti.halloween.repository.GameDataRepository;
import com.vti.halloween.security.UserPrincipal;
import com.vti.halloween.service.GameService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDataRepository gameDataRepository;

    @Override
    public Integer play() throws ApplicationException {
        Integer num = ThreadLocalRandom.current().nextInt(1, 52 + 1);
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("userPrincipal: {}", account);
            if (!gameDataRepository.existsAllByAccount(account)) {
                gameDataRepository.save(new GameDataEntity(account, num));
                return num;
            } else {
                throw new ApplicationException(HttpStatus.BAD_REQUEST, "Hết lượt chơi!");
            }
        } else {
            throw new InternalAuthenticationServiceException("Chưa đăng nhập!");
        }


    }

    @Override
    @Transactional
    public String reset(String account) throws ApplicationException {
        gameDataRepository.deleteAllByAccount(account);
        return "Delete account game data " + account + " successfully";
    }
}
