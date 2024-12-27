package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.StreamCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CategoryRepository extends JpaRepository<StreamCategory, String> {
    List<StreamCategory> findByIdIn(Collection<Long> ids);

    List<StreamCategory> findByIdInAndCategoryType(Collection<Long> ids, String categoryType);
}
