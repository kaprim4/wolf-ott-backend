package com.wolfott.mangement.epg.repositories;

import com.wolfott.mangement.epg.models.EpgApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ApiRepository extends JpaRepository<EpgApi, Long>, JpaSpecificationExecutor<EpgApi> {
}
