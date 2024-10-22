package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface StreamRepository extends JpaRepository<Stream, Long> {
    @Async
    CompletableFuture<List<Stream>> findByIdIn(Collection<Long> ids);
}
