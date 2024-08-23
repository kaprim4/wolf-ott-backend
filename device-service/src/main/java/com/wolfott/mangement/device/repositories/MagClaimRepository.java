package com.wolfott.mangement.device.repositories;

import com.wolfott.mangement.device.models.MagClaim;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MagClaimRepository extends JpaRepository<MagClaim, Long>, JpaSpecificationExecutor<MagClaim> {
    Optional<MagClaim> findByIdAndMagId(String clainId, Long deviceId);
    Page<MagClaim> findByMagId(Long deviceId, Pageable pageable);
    Page<MagClaim> findByMagId(Long deviceId, Specification<MagClaim> spec, Pageable pageable);
}
