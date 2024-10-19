package com.wolfott.stream_mangement.repositories;

import com.wolfott.stream_mangement.models.Stream;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StreamRepository extends JpaRepository<Stream, Long>, JpaSpecificationExecutor<Stream> {
    Page<Stream> findByType_TypeKey(String typeKey, Pageable pageable);
    Page<Stream> findByType_TypeKey(String typeKey, Specification<Stream> spec, Pageable pageable);
}
