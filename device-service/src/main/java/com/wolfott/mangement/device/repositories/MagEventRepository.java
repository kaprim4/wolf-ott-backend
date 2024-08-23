package com.wolfott.mangement.device.repositories;

import com.wolfott.mangement.device.models.MagEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface MagEventRepository extends JpaRepository<MagEvent, Long>, JpaSpecificationExecutor<MagEvent> {
    Optional<MagEvent> findByIdAndMagDeviceId(Long eventId, Long deviceId);
    Page<MagEvent> findByMagDeviceId(Long id, Pageable pageable);
    Page<MagEvent> findByMagDeviceId(Long id, Specification<MagEvent> spec, Pageable pageable);
}
