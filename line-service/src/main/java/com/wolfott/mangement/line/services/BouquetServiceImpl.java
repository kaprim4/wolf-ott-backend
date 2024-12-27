package com.wolfott.mangement.line.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.exceptions.BouquetNotFoundException;
import com.wolfott.mangement.line.exceptions.PresetNotFoundException;
import com.wolfott.mangement.line.mappers.CategoryMapper;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.models.Stream;
import com.wolfott.mangement.line.models.StreamCategory;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import com.wolfott.mangement.line.repositories.CategoryRepository;
import com.wolfott.mangement.line.repositories.StreamRepository;
import com.wolfott.mangement.line.responses.CategoryCompactResponse;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class BouquetServiceImpl implements BouquetService {

    @Autowired
    private BouquetRepository bouquetRepository;
    @Autowired
    private StreamRepository streamRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Optional<Bouquet> findById(Long id){
        return bouquetRepository.findById(id);
    }

    @Override
    public List<CategoryCompactResponse> getBouquetCategories(Long id) {
        // Fetch bouquet and associated JSON data
        Bouquet bouquet = bouquetRepository.findById(id)
                .orElseThrow(() -> new PresetNotFoundException("Bouquet Not Found"));

        String jsonChannels = bouquet.getBouquetChannels();
        String jsonMovies = bouquet.getBouquetMovies();
        String jsonSeries = bouquet.getBouquetSeries();
        String jsonStations = bouquet.getBouquetRadios();

        // ObjectMapper for JSON parsing
        ObjectMapper mapper = new ObjectMapper();

        // Create a list of all JSON strings
        List<String> jsonStrings = List.of(jsonChannels, jsonMovies, jsonSeries, jsonStations);

        List<Long> streamsIdx = jsonStrings.parallelStream()
                .flatMap(json -> parseJson(json, mapper).stream())
                .collect(Collectors.toList());

        // Fetch all the streams by their IDs
        List<Stream> streams = streamRepository.getByIdIn(streamsIdx);

// Collect all distinct category IDs directly
        Set<Long> categoryIdx = streams.stream().parallel()
                .map(Stream::getCategoryId)
                .flatMap(idx -> parseJson(idx, mapper).stream())
                .collect(Collectors.toSet());


        // Fetch all categories at once using the category IDs
        List<StreamCategory> categories = categoryRepository.findByIdIn(categoryIdx);

        // Return the mapped categories
        return categoryMapper.toCategoryCompactResponse(categories);
    }

    @Override
    public List<CategoryCompactResponse> getBouquetCategories(Long id, String type) {
        // Fetch bouquet and associated JSON data
        Bouquet bouquet = bouquetRepository.findById(id)
                .orElseThrow(() -> new PresetNotFoundException("Bouquet Not Found"));

        String jsonChannels = bouquet.getBouquetChannels();
        String jsonMovies = bouquet.getBouquetMovies();
        String jsonSeries = bouquet.getBouquetSeries();
        String jsonStations = bouquet.getBouquetRadios();

        // ObjectMapper for JSON parsing
        ObjectMapper mapper = new ObjectMapper();

        // Parse all indices in one go to avoid repeated mapper calls
        List<Long> channelsIdx = parseJson(jsonChannels, mapper);
        List<Long> moviesIdx = parseJson(jsonMovies, mapper);
        List<Long> seriesIdx = parseJson(jsonSeries, mapper);
        List<Long> stationsIdx = parseJson(jsonStations, mapper);

        // Combine all indices into a single list in a more efficient way
        List<Long> streamsIdx = java.util.stream.Stream.of(channelsIdx, moviesIdx, seriesIdx, stationsIdx)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        // Fetch all the streams by their IDs
        List<Stream> streams = streamRepository.getByIdIn(streamsIdx);

        // Collect all distinct category IDs directly
        Set<Long> categoryIdx = streams.stream()
                .map(Stream::getCategoryId)
                .map(Long::valueOf)
                .collect(Collectors.toSet());

        // Fetch all categories at once using the category IDs and type
        List<StreamCategory> categories = categoryRepository.findByIdInAndCategoryType(categoryIdx, type);

        // Return the mapped categories
        return categoryMapper.toCategoryCompactResponse(categories);
    }

    // Helper method to parse JSON (no parallelism)
    private List<Long> parseJson(String json, ObjectMapper mapper) {
        try {
            return Arrays.asList(mapper.readValue(json, Long[].class));  // Assuming it's a list of Longs
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse JSON: " + e.getMessage(), e);
        }
    }


    @Override
    public Bouquet save(Bouquet b) {
        return bouquetRepository.save(b);
    }
    @Override
    public void deleteById(Long id){
        bouquetRepository.deleteById(id);
    }
    @Override
    public Page<Bouquet> findAllWithFilters(Map<String, Object> filters, Pageable pageable) {
        Specification<Bouquet> spec = buildSpecification(filters);
        return bouquetRepository.findAll(spec, pageable);
    }
    @Override
    public List<Bouquet> findAllWithFilters(Map<String, Object> filters) {
        Specification<Bouquet> spec = buildSpecification(filters);
        return bouquetRepository.findAll(spec);
    }
    @Override
    public List<Bouquet> findAllByIds(List<Long> ids) {
        return bouquetRepository.findAllById(ids);
    }
    @Override
    public List<Bouquet> findAll() {
        return bouquetRepository.findAll();
    }

    private Specification<Bouquet> buildSpecification(Map<String, Object> filters) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (filters.containsKey("bouquetName")) {
                String bouquetName = filters.get("bouquetName").toString();
                predicate = cb.and(predicate, cb.like(root.get("bouquetName"), "%" + bouquetName + "%"));
            }
            if (filters.containsKey("bouquetOrder")) {
                Object bouquetOrderObj = filters.get("bouquetOrder");
                Integer bouquetOrder = Integer.valueOf(bouquetOrderObj.toString());
                predicate = cb.and(predicate, cb.equal(root.get("bouquetOrder"), bouquetOrder));
            }
            return predicate;
        };
    }
}