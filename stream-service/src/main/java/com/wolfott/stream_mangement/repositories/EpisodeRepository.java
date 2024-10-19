package com.wolfott.stream_mangement.repositories;

import com.wolfott.stream_mangement.models.StreamEpisode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EpisodeRepository extends JpaRepository<StreamEpisode, Long>, JpaSpecificationExecutor<StreamEpisode> {
}
