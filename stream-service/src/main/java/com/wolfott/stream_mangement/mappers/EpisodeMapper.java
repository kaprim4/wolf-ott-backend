package com.wolfott.stream_mangement.mappers;

import com.wolfott.stream_mangement.models.Stream;
import com.wolfott.stream_mangement.models.StreamEpisode;
import com.wolfott.stream_mangement.models.StreamSeries;
import com.wolfott.stream_mangement.responses.EpisodeCompactResponse;
import com.wolfott.stream_mangement.responses.EpisodeDetailResponse;
import com.wolfott.stream_mangement.responses.SerieDetailResponse;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class EpisodeMapper {
    private final ModelMapper modelMapper;
    private final SerieMapper serieMapper;
    @Autowired
    public EpisodeMapper(ModelMapper modelMapper, SerieMapper serieMapper) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper = modelMapper;
        this.serieMapper = serieMapper;
    }

    @PostConstruct
    private void setupMappings() {
        modelMapper.addMappings(new PropertyMap<StreamEpisode, EpisodeCompactResponse>() {
            @Override
            protected void configure() {
                using(streamToTitleConverter()).map(source.getStream(), destination.getTitle());
                using(serieToTitleConverter()).map(source.getSerie(), destination.getSerie());
                map().setNumber(source.getEpisodeNum());
            }
        });

        modelMapper.addMappings(new PropertyMap<StreamEpisode, EpisodeDetailResponse>() {
            @Override
            protected void configure() {
                using(streamToTitleConverter()).map(source.getStream(), destination.getTitle());
//                using(serieToTitleConverter()).map(source.getSerie(), destination.getSerie());
                map().setNumber(source.getEpisodeNum());
                using(serieToDetailResponse()).map(source.getSerie(), destination.getSerie());
            }
        });
    }

    private Converter<Stream, String> streamToTitleConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(Stream::getStreamDisplayName)
                .orElse(null); // "Anonymous"
    }

    private Converter<StreamSeries, String> serieToTitleConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(StreamSeries::getTitle)
                .orElse(null); // "Anonymous"
    }

    private Converter<StreamSeries, SerieDetailResponse> serieToDetailResponse(){
        return context -> Optional.ofNullable(context.getSource()).map(serieMapper::toSerieDetailResponse).orElse(null);
    }

    private Converter<Long, Date> expDateToDateConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(Date::new)
                .orElse(null);
    }

    // Convert Episode entity to CategoryCompactResponse
    public EpisodeCompactResponse toEpisodeCompactResponse(StreamEpisode episode) {
        return modelMapper.map(episode, EpisodeCompactResponse.class);
    }
    // Convert Episode entity to EpisodeDetailResponse
    public EpisodeDetailResponse toEpisodeDetailResponse(StreamEpisode serie) {
        return modelMapper.map(serie, EpisodeDetailResponse.class);
    }

    // Convert Page<Episode> to Page<EpisodeCompactResponse>
    public Page<EpisodeCompactResponse> toEpisodeCompactResponsePage(Page<StreamEpisode> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toEpisodeCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public List<EpisodeCompactResponse> toEpisodeCompactResponseList(List<StreamEpisode> list) {
        return list.stream().map(this::toEpisodeCompactResponse).toList();
    }
}
