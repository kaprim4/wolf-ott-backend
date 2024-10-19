package com.wolfott.stream_mangement.repositories;

import com.wolfott.stream_mangement.models.StreamSeries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SerieRepository extends JpaRepository<StreamSeries, Long>, JpaSpecificationExecutor<StreamSeries> {
}
