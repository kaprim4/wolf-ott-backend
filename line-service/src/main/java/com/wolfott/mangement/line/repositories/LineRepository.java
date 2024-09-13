package com.wolfott.mangement.line.repositories;

import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.LineList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LineRepository extends JpaRepository<Line, Long>, JpaSpecificationExecutor<Line> {

    @Query("SELECT l.id, l.username, l.password, u.username AS owner, l.enabled AS status, l.lastActivity AS online, l.isTrial AS trial, l.adminEnabled AS active, COUNT(la.activityId) AS connections, l.expDate AS expiration, l.updated AS lastConnection FROM Line l LEFT JOIN LineActivity la ON (la.activityId = l.lastActivity) LEFT JOIN User u ON (u.id = l.memberId) GROUP BY l.id, l.username, l.password, l.memberId, l.enabled, l.lastActivity, l.isTrial, l.adminEnabled, l.expDate, l.updated")
    List<LineList> findLinesList();
}
