package com.wolfott.mangement.epg.repositories;

import com.wolfott.mangement.epg.models.EpgLanguage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LanguageRepository extends JpaRepository<EpgLanguage, Long>, JpaSpecificationExecutor<EpgLanguage> {
}
