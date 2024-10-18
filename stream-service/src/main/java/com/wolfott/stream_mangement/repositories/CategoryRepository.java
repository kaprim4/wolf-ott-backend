package com.wolfott.stream_mangement.repositories;

import com.wolfott.stream_mangement.models.StreamCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<StreamCategory, Long>, JpaSpecificationExecutor<StreamCategory> {
}
