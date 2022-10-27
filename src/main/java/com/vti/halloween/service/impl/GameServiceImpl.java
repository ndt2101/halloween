package com.vti.halloween.service.impl;

import com.vti.halloween.exception.ApplicationException;
import com.vti.halloween.model.CardEntity;
import com.vti.halloween.model.GameDataEntity;
import com.vti.halloween.model.UserEntity;
import com.vti.halloween.repository.CardRepository;
import com.vti.halloween.repository.GameDataRepository;
import com.vti.halloween.repository.UserRepository;
import com.vti.halloween.security.UserPrincipal;
import com.vti.halloween.service.GameService;
import com.vti.halloween.utils.ExcelHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameDataRepository gameDataRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExcelHelper excelHelper;

    @Override
    public CardEntity play() throws ApplicationException {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
            String account = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            log.info("userPrincipal: {}", account);
//
            if (!gameDataRepository.existsAllByAccount(account) || account.equals("toan.nguyenxuan")) {
                CardEntity result = random(account);
//                for (int i = 0; i < 931; i++) {
//                    random(account);
//                }
                return result;
            } else {
                throw new ApplicationException(HttpStatus.BAD_REQUEST, "Hết lượt chơi!");
            }
        } else {
            throw new InternalAuthenticationServiceException("Chưa đăng nhập!");
        }
    }

    private CardEntity random(String account) throws ApplicationException {
        long totalUser = userRepository.count();
        long totalGameData = gameDataRepository.count();
        long totalWinPrize = gameDataRepository.countAllByWinPrize(true);
        double winPrizePercent = 0.15;
        int luckyRange = (int) (winPrizePercent * totalUser - totalWinPrize);
        log.info("Lucky range: {}", luckyRange);
        log.info("Total game data: {}", totalGameData);
        List<CardEntity> luckyCards = cardRepository.findAllByIsLuckyCard(1);
        List<CardEntity> unLuckyCards = cardRepository.findAllByIsLuckyCard(0);
        int num = 0;
        try {
            num = ThreadLocalRandom.current().nextInt(1, (int) (totalUser - totalGameData + 1));
        } catch (IllegalArgumentException e) {
            throw new ApplicationException(HttpStatus.BAD_REQUEST, "Toàn bộ user đã hết lượt");
        }

        CardEntity result = null;
        if (num <= luckyRange) {
            // trung giai
            int randomNum = ThreadLocalRandom.current().nextInt(0, luckyCards.size());
            result =  luckyCards.get(randomNum);
            gameDataRepository.save(new GameDataEntity(account, result, true));
        } else {
            //khong trung giai
            int randomNum = ThreadLocalRandom.current().nextInt(0, unLuckyCards.size());
            result =  unLuckyCards.get(randomNum);
            gameDataRepository.save(new GameDataEntity(account, result, false));
        }
        return result;
    }



    @Override
    @Transactional
    public String reset(String account) {
        gameDataRepository.deleteByAccount(account);
        return "Delete account game data " + account + " successfully";
    }

    @Override
    @Transactional
    public String resetAll() {
        gameDataRepository.deleteAll();
        return "Delete game data successfully";
    }

    @Override
    public String cartSeeding(MultipartFile file) {
        try {
            List<CardEntity> cardEntities = excelHelper.excelToCard(file.getInputStream());
            cardRepository.saveAll(cardEntities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store excel data: " + e.getMessage());
        }
        return "Upload successfully";
    }
}
