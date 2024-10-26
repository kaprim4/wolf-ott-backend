package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface BouquetRepository extends JpaRepository<Bouquet, Long>, JpaSpecificationExecutor<Bouquet> {
    List<Bouquet> findByPresetBouquets_Preset_Id(Long id);

    List<Bouquet> findByPresetBouquets_Preset_IdOrderByPresetBouquets_PositionOrderAsc(Long id);
}
