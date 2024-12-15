package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.PresetBouquet;
import com.wolfott.mangement.line.models.PresetBouquetId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresetBouquetRepository extends JpaRepository<PresetBouquet, PresetBouquetId> {
}
