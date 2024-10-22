package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface LineRepository extends JpaRepository<Line, Long> {
//    List<Line> findByIdIn(Collection<Long> ids);
    @Async
    CompletableFuture<List<Line>> findByIdIn(Collection<Long> ids);
}
