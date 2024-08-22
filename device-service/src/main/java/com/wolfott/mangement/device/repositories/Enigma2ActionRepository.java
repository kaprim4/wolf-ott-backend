package com.wolfott.mangement.device.repositories;

import com.wolfott.mangement.device.models.Enigma2Action;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface Enigma2ActionRepository extends JpaRepository<Enigma2Action, Long>, JpaSpecificationExecutor<Enigma2Action> {
    Optional<Enigma2Action> findByIdAndDeviceId(Long actionID, Long deviceId);
    Page<Enigma2Action> findByDeviceId(Long id, Pageable pageable);
    Page<Enigma2Action> findByDeviceId(Long id, Specification<Enigma2Action> spec, Pageable pageable);
}
