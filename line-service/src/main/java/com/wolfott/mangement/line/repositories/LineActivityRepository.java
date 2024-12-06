package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.LineActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LineActivityRepository extends JpaRepository<LineActivity, Long>, JpaSpecificationExecutor<LineActivity> {

    @Query("select a from LineActivity a WHERE a.activityId = :activityId")
    LineActivity findOneByLineID(Long activityId);

    @Query("select count(a.activityId) from LineActivity a WHERE a.activityId = :activityId AND a.active = true")
    int findCountByLineID(Long activityId);

    void deleteByActivityId(Long activityId);
}
