package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
