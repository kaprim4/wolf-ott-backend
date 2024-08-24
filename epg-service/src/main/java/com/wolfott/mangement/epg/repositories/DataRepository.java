package com.wolfott.mangement.epg.repositories;

import com.wolfott.mangement.epg.models.EpgData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DataRepository extends JpaRepository<EpgData, Long>, JpaSpecificationExecutor<EpgData> {
}
