package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.LineLive;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LineLiveRepository extends JpaRepository<LineLive, Long>, JpaSpecificationExecutor<LineLive> {

    @Query("select a from LineLive a WHERE a.userId = :id")
    LineLive findOneByLineID(Long id);
}
