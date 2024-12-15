package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.LineActivity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LineActivityRepository extends JpaRepository<LineActivity, Long>, JpaSpecificationExecutor<LineActivity> {

    @Query("select a from LineActivity a WHERE a.activityId = :activityId")
    LineActivity findOneByLineID(Long activityId);

    @Query("select count(a.activityId) from LineActivity a WHERE a.activityId = :activityId AND a.active = true")
    int findCountByLineID(Long activityId);

    void deleteByActivityId(Long activityId);

    Page<LineActivity> findByUserId(Long userId, Pageable pageable);

    @Query("select a from LineActivity a LEFT JOIN Line l ON (a.activityId = l.id) WHERE l.id IN :lineIds")
    Page<LineActivity> findByLineIdIn(List<Long> lineIds, Pageable pageable);

    Page<LineActivity> findByActivityIdIn(List<Long> ids, Pageable pageable);
}
