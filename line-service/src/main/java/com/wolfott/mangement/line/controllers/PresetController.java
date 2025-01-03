package com.wolfott.mangement.line.controllers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wolfott.mangement.line.dto.UserDTO;
import com.wolfott.mangement.line.models.*;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import com.wolfott.mangement.line.requests.PresetCreateRequest;
import com.wolfott.mangement.line.requests.PresetUpdateRequest;
import com.wolfott.mangement.line.responses.*;
import com.wolfott.mangement.line.services.BouquetService;
import com.wolfott.mangement.line.services.LineService;
import com.wolfott.mangement.line.services.PresetService;
import com.wolfott.mangement.line.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/presets")
public class PresetController {
    @Autowired
    private BouquetRepository bouquetRepository;

    @Autowired
    UserService userService;

    @Autowired
    PresetService presetService;

    @Autowired
    BouquetService bouquetService;

    @Autowired
    LineService lineService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{id}")
    public PresetDetailResponse getOne(@PathVariable Long id) {
        Preset preset = presetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Preset not found"));
        return toPresetDetailResponse(preset);
    }

    @GetMapping("/{id}/bouquets")
    public List<BouquetCompactResponse> getPresetBouquets(@PathVariable Long id) {
        Preset preset = presetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Preset not found"));
        List<Bouquet> sortedBouquets = preset.getPresetBouquets().stream()
                .sorted(Comparator.comparing(PresetBouquet::getPositionOrder))
                .map(PresetBouquet::getBouquet)
                .toList();
        return sortedBouquets.stream()
                .map(this::toBouquetCompactResponse)
                .toList();
    }

    @GetMapping
    public Page<PresetCompactResponse> getAll(Pageable pageable) {
        Page<Preset> page = presetService.findAll(pageable);
        return page.map(this::toPresetCompactResponse);
    }

    @GetMapping("/list")
    public List<PresetCompactResponse> getAll() {
        List<Preset> presets = presetService.findAllByUserIdAsList();
        return presets.stream().map(this::toPresetCompactResponse).toList();
    }

    @PostMapping
    public PresetCreateResponse createOne(@RequestBody PresetCreateRequest request) {
        User user = userService.findById(request.getUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
        log.info("USER: {}", user.toString());

        Preset preset = new Preset();
        preset.setUser(user);
        preset.setPresetName(request.getPresetName());
        preset.setPresetDescription(request.getPresetDescription());
        preset.setStatus(Boolean.TRUE.equals(request.getStatus())); // si request.getStatus() est null, on obtient false
        preset.setCreatedAt(LocalDateTime.now());
        preset.setUpdatedAt(LocalDateTime.now());

        if (request.getBouquets() != null && !request.getBouquets().isEmpty()) {
            int order = 0;
            for (Long bouquetId : request.getBouquets()) {
                Optional<Bouquet> bouquetEntity = bouquetService.findById(bouquetId);
                if (bouquetEntity.isPresent()) {
                    preset.addBouquet(bouquetEntity.get(), order);
                    order++;
                }
            }
        }
        Preset saved = presetService.save(preset);

        return PresetCreateResponse.builder()
                .user(user != null ? new UserDTO(user) : null)
                .presetName(saved.getPresetName())
                .presetDescription(saved.getPresetDescription())
                .status(saved.getStatus())
                .bouquets(request.getBouquets())
                .createdAt(saved.getCreatedAt())
                .updatedAt(saved.getUpdatedAt())
                .build();
    }

    @PutMapping("/{id}")
    public PresetUpdateResponse updateOne(@PathVariable("id") Long id, @RequestBody PresetUpdateRequest request) {
        Preset preset = presetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Preset not found"));

        User user = userService.findById(request.getUser().getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        preset.setUser(user);
        preset.setPresetName(request.getPresetName());
        preset.setPresetDescription(request.getPresetDescription());
        preset.setStatus(Boolean.TRUE.equals(request.getStatus()));
        preset.setCreatedAt(preset.getCreatedAt());
        preset.setUpdatedAt(LocalDateTime.now());

        preset.getPresetBouquets().clear();
        if (request.getBouquets() != null && !request.getBouquets().isEmpty()) {
            int order = 0;
            for (Long bouquetId : request.getBouquets()) {
                Optional<Bouquet> bouquetEntity = bouquetService.findById(bouquetId);
                if (bouquetEntity.isPresent()) {
                    preset.addBouquet(bouquetEntity.get(), order);
                    order++;
                }
            }
        }

        Preset updated = presetService.save(preset);

        if (request.getBouquets() != null && !request.getBouquets().isEmpty()) {
            List<Line> lineList = lineService.getLinesByPresetId(preset.getId());
            if(lineList != null && !lineList.isEmpty()) {
                for (Line line : lineList) {
                    line.setBouquet(convertListToJson(request.getBouquets()));
                }
                lineService.saveAll(lineList);
            }
        }

        return PresetUpdateResponse.builder()
                .user(user != null ? new UserDTO(user) : null)
                .presetName(updated.getPresetName())
                .presetDescription(updated.getPresetDescription())
                .status(updated.getStatus())
                .bouquets(request.getBouquets())
                .createdAt(updated.getCreatedAt())
                .updatedAt(updated.getUpdatedAt())
                .build();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        Preset preset = presetService.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Preset not found"));
        presetService.deleteById(preset.getId());
    }

    // Méthode de conversion pour PresetDetailResponse :
    private PresetDetailResponse toPresetDetailResponse(Preset preset) {
        User user = null;
        if (preset.getUser() != null)
            user = userService.findById(preset.getUser().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Long> bouquetIds = preset.getPresetBouquets().stream()
                .sorted(Comparator.comparing(PresetBouquet::getPositionOrder))
                .map(pb -> pb.getBouquet().getId())
                .toList();

        return new PresetDetailResponse(
                preset.getId(),
                user != null ? new UserDTO(user) : null,
                preset.getPresetName(),
                preset.getPresetDescription(),
                preset.getStatus(),
                bouquetIds,
                preset.getCreatedAt() != null ? preset.getCreatedAt() : null,
                preset.getUpdatedAt() != null ? preset.getUpdatedAt() : null
        );
    }

    // Méthode de conversion pour PresetCompactResponse :
    private PresetCompactResponse toPresetCompactResponse(Preset preset) {
        User user = null;
        if (preset.getUser() != null)
            user = userService.findById(preset.getUser().getId())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        List<Long> bouquetIds = preset.getPresetBouquets().stream()
                .sorted(Comparator.comparing(PresetBouquet::getPositionOrder))
                .map(pb -> pb.getBouquet().getId())
                .toList();

        return new PresetCompactResponse(
                preset.getId(),
                user != null ? new UserDTO(user) : null,
                preset.getPresetName(),
                preset.getPresetDescription(),
                bouquetIds,
                preset.getStatus(),
                preset.getCreatedAt() != null ? preset.getCreatedAt() : null,
                preset.getUpdatedAt() != null ? preset.getUpdatedAt() : null
        );
    }

    private BouquetCompactResponse toBouquetCompactResponse(Bouquet b) {
        List<Long> channelIds = parseIdList(b.getBouquetChannels());
        List<Long> movieIds   = parseIdList(b.getBouquetMovies());
        List<Long> seriesIds  = parseIdList(b.getBouquetSeries());
        List<Long> radioIds   = parseIdList(b.getBouquetRadios());
        return new BouquetCompactResponse(
                b.getId(),
                b.getBouquetName(),
                b.getBouquetOrder(),
                channelIds.size(),
                movieIds.size(),
                seriesIds.size(),
                radioIds.size()
        );
    }

    private List<Long> parseIdList(String jsonString) {
        if (jsonString == null || jsonString.trim().isEmpty()) {
            return List.of();
        }
        try {
            return objectMapper.readValue(jsonString, new TypeReference<List<Long>>() {});
        } catch (Exception e) {
            return List.of();
        }
    }

    private static String convertListToJson(List<Long> ids) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.writeValueAsString(ids); // Convertit en JSON : "[67,66,65]"
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la conversion de la liste en JSON", e);
        }
    }
}
