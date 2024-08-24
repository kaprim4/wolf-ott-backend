package com.wolfott.mangement.epg.repositories;

import com.wolfott.mangement.epg.models.EpgData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface DataRepository extends JpaRepository<EpgData, Long>, JpaSpecificationExecutor<EpgData> {
    Optional<EpgData> findByIdAndEpg_Id(Long data_id, Long epgId);
    Page<EpgData> findByEpg_Id(Long epgId, Pageable pageable);
    Page<EpgData> findByEpg_Id(Long epgId, Specification<EpgData> spec, Pageable pageable);
}
