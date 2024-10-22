package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findOneById(Long id);

//    List<User> findByIdIn(Collection<Long> ids);
    @Async
    CompletableFuture<List<User>> findByIdIn(Collection<Long> ids);
}
