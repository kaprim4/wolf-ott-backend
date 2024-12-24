package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.exceptions.UnauthorizedAccessException;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.Preset;
import com.wolfott.mangement.line.models.User;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import com.wolfott.mangement.line.repositories.PresetRepository;
import com.wolfott.mangement.line.repositories.UserRepository;
import com.wolfott.mangement.line.specifications.LineSpecifications;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PresetService {

    @Autowired
    private PresetRepository presetRepository;
    @Autowired
    private UserRepository userRepository;

    public Optional<Preset> findById(Long id) {
        return presetRepository.findById(id);
    }

    public Preset save(Preset p) {
        return presetRepository.save(p);
    }

    public void deleteById(Long id) {
        presetRepository.deleteById(id);
    }

    public Page<Preset> findAll(Pageable pageable) {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        return presetRepository.findAllByUserId(ownerId, pageable);
    }

    public List<Preset> findAllByUserIdAsList() {
        Long ownerId = getCurrentUserId();
        if (ownerId == null) {
            throw new UnauthorizedAccessException("User not authenticated");
        }
        return presetRepository.findAllByUserIdAsList(ownerId);
    }

    private Authentication getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    private Long getCurrentUserId() {
        Authentication auth = getPrincipal();
        if (auth != null) {
            Object principal = auth.getPrincipal();
            System.out.println("Principal: " + principal);
            if (principal instanceof User) {
                return ((User) principal).getId();
            }
        }
        return null;
    }

    private boolean isAdmin() {
        Authentication auth = getPrincipal();
        Object principal = auth.getPrincipal();
        if (principal instanceof User) {
            return ((User) principal).isAdmin();
        }
        return false;
    }
}