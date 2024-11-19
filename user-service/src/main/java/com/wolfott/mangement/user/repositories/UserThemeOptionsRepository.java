package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.UserThemeOptions;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserThemeOptionsRepository extends JpaRepository<UserThemeOptions, Long> {

    Optional<UserThemeOptions> findByUserId(Long userId);
}
