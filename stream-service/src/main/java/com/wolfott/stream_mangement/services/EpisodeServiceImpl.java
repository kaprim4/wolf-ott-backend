package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.exceptions.EpisodeNotFoundException;
import com.wolfott.stream_mangement.mappers.EpisodeMapper;
import com.wolfott.stream_mangement.models.StreamEpisode;
import com.wolfott.stream_mangement.repositories.EpisodeRepository;
import com.wolfott.stream_mangement.responses.EpisodeCompactResponse;
import com.wolfott.stream_mangement.responses.EpisodeDetailResponse;
import com.wolfott.stream_mangement.responses.SerieCompactResponse;
import com.wolfott.stream_mangement.specifications.EpisodeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private EpisodeSpecification episodeSpecification;
    @Autowired
    private EpisodeMapper episodeMapper;
    @Override
    public EpisodeDetailResponse getOne(Long id) {
        StreamEpisode episode = episodeRepository.findById(id).orElseThrow(EpisodeNotFoundException::new);
        return episodeMapper.toEpisodeDetailResponse(episode);
    }

    @Override
    public List<EpisodeCompactResponse> getAll() {
        List<StreamEpisode> episodes = episodeRepository.findAll();
        return episodeMapper.toEpisodeCompactResponseList(episodes);
    }

    @Override
    public List<EpisodeCompactResponse> getAll(Map<String, Object> filters) {
        Specification<StreamEpisode> spec = episodeSpecification.dynamic(filters);
        List<StreamEpisode> episodes = episodeRepository.findAll(spec);
        return episodeMapper.toEpisodeCompactResponseList(episodes);
    }

    @Override
    public Page<EpisodeCompactResponse> getAll(Pageable pageable) {
        Page<StreamEpisode> episodes = episodeRepository.findAll(pageable);
        return episodeMapper.toEpisodeCompactResponsePage(episodes);
    }

    @Override
    public Page<EpisodeCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<StreamEpisode> spec = episodeSpecification.dynamic(filters);
        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by(Sort.Order.asc("serie.id"), // Assuming 'title' is a field in StreamSeries
                            Sort.Order.asc("seasonNum"),
                            Sort.Order.asc("episodeNum")));
        }
        Page<StreamEpisode> episodes = episodeRepository.findAll(spec, pageable);
        return episodeMapper.toEpisodeCompactResponsePage(episodes);
    }
}
