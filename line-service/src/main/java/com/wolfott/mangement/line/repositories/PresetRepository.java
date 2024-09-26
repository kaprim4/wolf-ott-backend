package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Preset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PresetRepository extends JpaRepository<Preset, Long>, JpaSpecificationExecutor<Preset> {
}
