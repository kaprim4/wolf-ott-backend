package com.wolfott.mangement.administration.repositories;

import com.wolfott.mangement.administration.models.Ranking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankingRepository extends JpaRepository<Ranking, Long> {
}
