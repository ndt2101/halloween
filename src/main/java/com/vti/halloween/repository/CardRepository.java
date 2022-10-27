package com.vti.halloween.repository;

import com.vti.halloween.model.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface CardRepository extends JpaRepository<CardEntity, Long> {
    List<CardEntity> findAllByIsLuckyCard(int isLuckyCard);
}
