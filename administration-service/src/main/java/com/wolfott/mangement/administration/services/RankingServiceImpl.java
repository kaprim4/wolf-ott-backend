package com.wolfott.mangement.administration.services;

import com.wolfott.mangement.administration.exceptions.RankingNotFoundException;
import com.wolfott.mangement.administration.models.Ranking;
import com.wolfott.mangement.administration.repositories.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RankingServiceImpl implements RankingService {
    @Autowired
    private RankingRepository rankingRepository;

    @Override
    public List<Ranking> getAll() {
        return rankingRepository.findAll();
    }

    @Override
    public Page<Ranking> getAll(Pageable pageable) {
        return rankingRepository.findAll(pageable);
    }

    @Override
    public Ranking getOne(Long id) {
        return rankingRepository.findById(id).orElseThrow(RankingNotFoundException::new);
    }

    @Override
    public Ranking create(Ranking ranking) {
        return rankingRepository.save(ranking);
    }

    @Override
    public Ranking update(Long id, Ranking ranking) {
        ranking.setId(id);
        return rankingRepository.save(ranking);
    }

    @Override
    public void delete(Long id) {
        rankingRepository.deleteById(id);
    }
}
