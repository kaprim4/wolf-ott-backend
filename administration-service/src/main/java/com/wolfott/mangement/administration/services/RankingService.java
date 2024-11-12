package com.wolfott.mangement.administration.services;


import com.wolfott.mangement.administration.models.Ranking;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RankingService {

    List<Ranking> getAll();

    Page<Ranking> getAll(Pageable pageable);

    Ranking getOne(Long id);

    Ranking create(Ranking ranking);

    Ranking update(Long id, Ranking ranking);

    void delete(Long id);
}
