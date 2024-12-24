package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Preset;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PresetRepository extends JpaRepository<Preset, Long>, JpaSpecificationExecutor<Preset> {

    @Query("SELECT p FROM Preset p WHERE p.user.id = :userId")
    Page<Preset> findAllByUserId(Long userId, Pageable pageable);
}
