package com.wolfott.mangement.administration.controllers;

import com.wolfott.mangement.administration.models.Ranking;
import com.wolfott.mangement.administration.services.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/ranks")
public class RankingController {
    @Autowired
    private RankingService rankingService;

    @GetMapping
    public Page<Ranking> getAll(Pageable pageable){
        return rankingService.getAll(pageable);
    }

    @GetMapping("/{id}")
    public Ranking getAll(@PathVariable("id") Long id){
        return rankingService.getOne(id);
    }

    @PostMapping
    public Ranking create(@RequestBody Ranking ranking){
        return rankingService.create(ranking);
    }
    @PutMapping("/{id}")
    public Ranking create(@PathVariable("id") Long id, @RequestBody Ranking ranking){
        return rankingService.update(id, ranking);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        rankingService.delete(id);
    }
}
