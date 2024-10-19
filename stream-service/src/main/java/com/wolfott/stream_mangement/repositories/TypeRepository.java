package com.wolfott.stream_mangement.repositories;

import com.wolfott.stream_mangement.models.StreamType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRepository extends JpaRepository<StreamType, String> {
}
