package com.wolfott.mangement.line.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.requests.BouquetCreateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.requests.PresetBouquetCategoryCreateRequest;
import com.wolfott.mangement.line.requests.PresetBouquetCategoryUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.services.BouquetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/bouquets")
public class BouquetController {

    @Autowired
    private BouquetService bouquetService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{id}")
    public BouquetDetailResponse getOne(@PathVariable("id") Long id){
        Bouquet bouquet = bouquetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bouquet not found"));

        return toBouquetDetailResponse(bouquet);
    }

    @GetMapping("/{bouquet_id}/categories")
    public List<CategoryCompactResponse> getCategories(@PathVariable("bouquet_id") Long id){
        return bouquetService.getBouquetCategories(id);
    }

    @GetMapping("/presets")
    public Page<PresetBouquetCategoryDetailResponse> getAllBouquetsPresets(Pageable pageable){
        return bouquetService.getAllBouquetsPresets(pageable);
    }
    @PostMapping("/presets")
    public PresetBouquetCategoryCreateResponse savePresetBouquetCategory(@RequestBody PresetBouquetCategoryCreateRequest request){
        return bouquetService.savePresetBouquetCategory(request);
    }

    @PostMapping("/presets/{id}")
    public PresetBouquetCategoryUpdateResponse savePresetBouquetCategory(@PathVariable("id") Long id, @RequestBody PresetBouquetCategoryUpdateRequest request){
        return bouquetService.updatePresetBouquetCategory(id, request);
    }

    @GetMapping("/{id}/categories/{type}")
    public List<CategoryCompactResponse> getCategories(@PathVariable("id") Long id, @PathVariable("type") String type){
        return bouquetService.getBouquetCategories(id);
    }

    @GetMapping("/list")
    public List<BouquetCompactResponse> getAll(@RequestParam Map<String, Object> filters){
        List<Bouquet> bouquets = bouquetService.findAllWithFilters(filters);
        return bouquets.stream().map(this::toBouquetCompactResponse).toList();
    }

    @GetMapping
    public Page<BouquetCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        Page<Bouquet> page = bouquetService.findAllWithFilters(filters, pageable);
        return page.map(this::toBouquetCompactResponse);
    }

    @PostMapping
    public BouquetCreateResponse create(@RequestBody BouquetCreateRequest request){
        Bouquet bouquet = new Bouquet();
        bouquet.setBouquetName(request.getBouquetName());
        bouquet.setBouquetChannels(request.getBouquetChannels());
        bouquet.setBouquetMovies(request.getBouquetMovies());
        bouquet.setBouquetRadios(request.getBouquetRadios());
        bouquet.setBouquetSeries(request.getBouquetSeries());
        bouquet.setBouquetOrder(request.getBouquetOrder());

        Bouquet saved = bouquetService.save(bouquet);
        return BouquetCreateResponse.builder()
                .id(saved.getId())
                .bouquetName(saved.getBouquetName())
                .bouquetChannels(saved.getBouquetChannels())
                .bouquetMovies(saved.getBouquetMovies())
                .bouquetRadios(saved.getBouquetRadios())
                .bouquetSeries(saved.getBouquetSeries())
                .bouquetOrder(saved.getBouquetOrder())
                .build();
    }

    @PutMapping("/{id}")
    public BouquetUpdateResponse update(@PathVariable("id") Long id, @RequestBody BouquetUpdateRequest request){
        Bouquet bouquet = bouquetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bouquet not found"));

        bouquet.setBouquetName(request.getBouquetName());
        bouquet.setBouquetChannels(request.getBouquetChannels());
        bouquet.setBouquetMovies(request.getBouquetMovies());
        bouquet.setBouquetRadios(request.getBouquetRadios());
        bouquet.setBouquetSeries(request.getBouquetSeries());
        bouquet.setBouquetOrder(request.getBouquetOrder());

        Bouquet updated = bouquetService.save(bouquet);
        return BouquetUpdateResponse.builder()
                .id(updated.getId())
                .bouquetName(updated.getBouquetName())
                .bouquetChannels(updated.getBouquetChannels())
                .bouquetMovies(updated.getBouquetMovies())
                .bouquetRadios(updated.getBouquetRadios())
                .bouquetSeries(updated.getBouquetSeries())
                .bouquetOrder(updated.getBouquetOrder())
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        Bouquet bouquet = bouquetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bouquet not found"));

        bouquetService.deleteById(bouquet.getId());
    }

    private BouquetDetailResponse toBouquetDetailResponse(Bouquet bouquet) {
        return BouquetDetailResponse.builder()
                .id(bouquet.getId())
                .bouquetName(bouquet.getBouquetName())
                .build();
    }

    private BouquetCompactResponse toBouquetCompactResponse(Bouquet b) {
        List<Long> channelIds = parseIdList(b.getBouquetChannels());
        List<Long> movieIds = parseIdList(b.getBouquetMovies());
        List<Long> seriesIds = parseIdList(b.getBouquetSeries());
        List<Long> radioIds = parseIdList(b.getBouquetRadios());

        Integer streams = channelIds.size();
        Integer movies = movieIds.size();
        Integer series = seriesIds.size();
        Integer stations = radioIds.size();

        return new BouquetCompactResponse(
                b.getId(),
                b.getBouquetName(),
                b.getBouquetOrder(),
                streams,
                movies,
                series,
                stations
        );
    }

    private List<Long> parseIdList(String jsonString) {
        if (jsonString == null || jsonString.trim().isEmpty()) {
            return List.of();
        }
        try {
            return objectMapper.readValue(jsonString, new TypeReference<List<Long>>() {});
        } catch (Exception e) {
            // Si le parsing échoue, on retourne une liste vide ou on gère l'exception différemment.
            return List.of();
        }
    }
}