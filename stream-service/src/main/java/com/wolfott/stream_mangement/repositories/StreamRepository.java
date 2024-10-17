package com.wolfott.stream_mangement.repositories;

import com.wolfott.stream_mangement.models.Stream;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StreamRepository extends JpaRepository<Stream, Long>, JpaSpecificationExecutor<Stream> {
}
