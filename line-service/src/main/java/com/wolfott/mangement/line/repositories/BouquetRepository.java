package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Bouquet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetRepository extends JpaRepository<Bouquet, Long>, JpaSpecificationExecutor<Bouquet> {

}