package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.exceptions.EpisodeNotFoundException;
import com.wolfott.stream_mangement.mappers.EpisodeMapper;
import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.models.StreamEpisode;
import com.wolfott.stream_mangement.models.StreamSeries;
import com.wolfott.stream_mangement.repositories.EpisodeRepository;
import com.wolfott.stream_mangement.repositories.SerieRepository;
import com.wolfott.stream_mangement.repositories.StreamRepository;
import com.wolfott.stream_mangement.responses.EpisodeCompactResponse;
import com.wolfott.stream_mangement.responses.EpisodeDetailResponse;
import com.wolfott.stream_mangement.responses.SerieCompactResponse;
import com.wolfott.stream_mangement.specifications.EpisodeSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
public class EpisodeServiceImpl implements EpisodeService {
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private SerieRepository serieRepository;
    @Autowired
    private StreamRepository streamRepository;
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
        List<StreamEpisode> episodes = episodeRepository.findAll().parallelStream().map(episode -> {
            StreamSeries series = serieRepository.findById(episode.getSeriesId()).orElse(null);
            episode.setSerie(series);
            Stream stream = streamRepository.findById(episode.getStreamId()).orElse(null);
            episode.setStream(stream);
            return episode;
        }).collect(Collectors.toList());
        return episodeMapper.toEpisodeCompactResponseList(episodes);
    }

    @Override
    public List<EpisodeCompactResponse> getAll(Map<String, Object> filters) {
        Specification<StreamEpisode> spec = episodeSpecification.dynamic(filters);
        List<StreamEpisode> episodes = episodeRepository.findAll(spec).parallelStream().map(episode -> {
            StreamSeries series = serieRepository.findById(episode.getSeriesId()).orElse(null);
            episode.setSerie(series);
            Stream stream = streamRepository.findById(episode.getStreamId()).orElse(null);
            episode.setStream(stream);
            return episode;
        }).collect(Collectors.toList());
        return episodeMapper.toEpisodeCompactResponseList(episodes);
    }

    @Override
    public Page<EpisodeCompactResponse> getAll(Pageable pageable) {
        Page<StreamEpisode> episodes = episodeRepository.findAll(pageable).map(episode -> {
            StreamSeries series = serieRepository.findById(episode.getSeriesId()).orElse(null);
            episode.setSerie(series);
            Stream stream = streamRepository.findById(episode.getStreamId()).orElse(null);
            episode.setStream(stream);
            return episode;
        });
        return episodeMapper.toEpisodeCompactResponsePage(episodes);
    }


    @Override
    public Page<EpisodeCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<StreamEpisode> spec = episodeSpecification.dynamic(filters);

        if (pageable.getSort().isUnsorted()) {
            pageable = PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(),
                    Sort.by(Sort.Order.asc("seasonNum"), Sort.Order.asc("episodeNum")));
        }

        Page<StreamEpisode> episodes = episodeRepository.findAll(spec, pageable);

        List<CompletableFuture<StreamEpisode>> episodeFutures = episodes.getContent().stream()
                .map(episode -> CompletableFuture.supplyAsync(() -> {
                    CompletableFuture<StreamSeries> seriesFuture = CompletableFuture.supplyAsync(() -> {
                        try {
                            return serieRepository.findById(episode.getSeriesId()).orElse(null);
                        } catch (Exception e) {
                            return null;
                        }
                    });

                    CompletableFuture<Stream> streamFuture = CompletableFuture.supplyAsync(() -> {
                        try {
                            return streamRepository.findById(episode.getStreamId()).orElse(null);
                        } catch (Exception e) {
                            return null;
                        }
                    });

                    CompletableFuture<Void> allOf = CompletableFuture.allOf(seriesFuture, streamFuture);
                    allOf.join();

                    episode.setSerie(seriesFuture.join());
                    episode.setStream(streamFuture.join());

                    return episode;
                }))
                .collect(Collectors.toList());

        List<StreamEpisode> completedEpisodes = episodeFutures.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());

        Page<StreamEpisode> completedEpisodesPage = new PageImpl<>(completedEpisodes, pageable, episodes.getTotalElements());

        return episodeMapper.toEpisodeCompactResponsePage(completedEpisodesPage);
    }


}
