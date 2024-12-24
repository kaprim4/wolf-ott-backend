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

    public List<Preset> findAllWithFilters(Map<String, Object> filters) {
        Specification<Preset> spec = buildSpecification(filters);
        return presetRepository.findAll(spec);
    }

    private Specification<Preset> buildSpecification(Map<String, Object> filters) {
        return (root, query, cb) -> {
            Predicate predicate = cb.conjunction();
            if (filters.containsKey("presetName")) {
                String presetName = filters.get("presetName").toString();
                predicate = cb.and(predicate, cb.like(root.get("presetName"), "%" + presetName + "%"));
            }
            if (filters.containsKey("status")) {
                Object statusObj = filters.get("status");
                boolean status = Boolean.parseBoolean(statusObj.toString());
                predicate = cb.and(predicate, cb.equal(root.get("status"), status));
            }
            return predicate;
        };
    }


    // Helper method to get current authenticated user's role
    private Authentication getPrincipal() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    // Helper method to get user ID (or any other user details you need)
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


    // Helper method to check if the current user is an admin
    private boolean isAdmin() {
        Authentication auth = getPrincipal();
        Object principal = auth.getPrincipal();
//        return auth != null && auth.getAuthorities().stream().anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ROLE_ADMIN"));
        if (principal instanceof User) {
            return ((User) principal).isAdmin();
        }
        return false;
    }
}