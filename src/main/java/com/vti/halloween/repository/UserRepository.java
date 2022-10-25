package com.vti.halloween.repository;

import com.vti.halloween.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByAccount(String loginName);
    @NonNull
    Optional<UserEntity> findById(@NonNull Long id);
    Optional<UserEntity> findByAccount(String loginName);
}

