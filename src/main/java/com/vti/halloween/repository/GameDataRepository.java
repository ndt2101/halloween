package com.vti.halloween.repository;

import com.vti.halloween.model.GameDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameDataRepository extends JpaRepository<GameDataEntity, Long> {
    boolean existsAllByAccount(String account);

    void deleteAllByAccount(String account);
}
