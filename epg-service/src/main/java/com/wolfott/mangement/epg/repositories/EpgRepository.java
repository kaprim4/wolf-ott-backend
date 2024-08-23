package com.wolfott.mangement.epg.repositories;

import com.wolfott.mangement.epg.models.Epg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EpgRepository extends JpaRepository<Epg, Long>, JpaSpecificationExecutor<Epg> {
}
