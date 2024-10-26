package com.wolfott.mangement.line.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.requests.BouquetCreateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.responses.BouquetCompactResponse;
import com.wolfott.mangement.line.responses.BouquetCreateResponse;
import com.wolfott.mangement.line.responses.BouquetDetailResponse;
import com.wolfott.mangement.line.responses.BouquetUpdateResponse;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class BouquetMapper {
    @Autowired
    ModelMapper mapper;

    @PostConstruct
    private void setupMappings(){
        mapper.addMappings(new PropertyMap<Bouquet, BouquetCompactResponse>() {

            @Override
            protected void configure() {
                map().setId(source.getId());
                map().setBouquetName(source.getBouquetName());
                map().setBouquetOrder(source.getBouquetOrder());

                using(countJsonArray()).map(source.getBouquetChannels(), destination.getStreams());
                using(countJsonArray()).map(source.getBouquetMovies(), destination.getMovies());
                using(countJsonArray()).map(source.getBouquetSeries(), destination.getSeries());
                using(countJsonArray()).map(source.getBouquetRadios(), destination.getStations());

            }
        });
    }

    private Converter<String, Integer> countJsonArray(){
        return context -> Optional.ofNullable(context.getSource()).stream().map(this::countItems).findAny().orElse(0);
    }
    private Integer countItems(String jsonArray) {
        ObjectMapper objectMapper = new ObjectMapper();
        if (jsonArray == null || jsonArray.trim().isEmpty() || jsonArray.equals("[]")) {
            return 0;
        }

        try {
            JsonNode node = objectMapper.readTree(jsonArray);
            return node.size(); // Get the count of elements in the JSON array
        } catch (Exception e) {
            // Handle the exception (log it, rethrow it, etc.)
            return 0;
        }
    }

    public Bouquet toBouquet(BouquetCreateRequest request){
        return mapper.map(request, Bouquet.class);
    }
    public Bouquet toBouquet(BouquetUpdateRequest request){
        return mapper.map(request, Bouquet.class);
    }

    public BouquetCreateResponse toCreateResponse(Bouquet model){
        return mapper.map(model, BouquetCreateResponse.class);
    }

    public BouquetUpdateResponse toUpdateResponse(Bouquet model){
        return mapper.map(model, BouquetUpdateResponse.class);
    }

    public BouquetDetailResponse toDetailResponse(Bouquet model){
        return mapper.map(model, BouquetDetailResponse.class);
    }

    public BouquetCompactResponse toCompactResponse(Bouquet model){
        return mapper.map(model, BouquetCompactResponse.class);
    }

    public Page<BouquetCompactResponse> toCompactResponse(Page<Bouquet> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<BouquetCompactResponse> toCompactResponse(Collection<Bouquet> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }

    public List<BouquetCompactResponse> toCompactResponse(List<Bouquet> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
