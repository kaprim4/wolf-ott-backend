package com.wolfott.mangement.line.services;

import com.wolfott.mangement.line.configs.UserServiceClient;
import com.wolfott.mangement.line.dto.LineCompact;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.repositories.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class LineService {

    @Autowired
    LineRepository lineRepository;

    @Autowired
    private UserServiceClient userServiceClient;

    public Page<LineCompact> getAllLines(Pageable pageable) {
        return lineRepository.findAll(pageable).map(this::convertToCompact);
    }

    public void delete(Long id) {
        lineRepository.deleteById(id);
    }

    private LineCompact convertToCompact(Line line) {
        LineCompact compact = new LineCompact();
        compact.setId(line.getId());
        compact.setUsername(line.getUsername());
        compact.setPassword(line.getPassword());
        // Fetch the username from user-service using member_id
        String ownerUsername = userServiceClient.getUsernameByMemberId(line.getMemberId());
        compact.setOwner(ownerUsername != null ? ownerUsername : "Unknown");

        compact.setStatus(line.getEnabled() != null && line.getEnabled() == 1 ? "Active" : "Inactive");
        compact.setTrial(line.getIsTrial() != null && line.getIsTrial() ? "Yes" : "No");
        compact.setExpiration(line.getExpDate() != null ? line.getExpDate().toString() : "N/A");
        compact.setLastConnection(line.getUpdated() != null ? line.getUpdated().toString() : "N/A");
        return compact;
    }
}
