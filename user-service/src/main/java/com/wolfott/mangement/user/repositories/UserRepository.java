package com.wolfott.mangement.user.repositories;

import com.wolfott.mangement.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findOneById(Long id);

//    List<User> findByIdIn(Collection<Long> ids);
    @Async
    CompletableFuture<List<User>> findByIdIn(Collection<Long> ids);

    // Fetch all users who are direct subsellers of a given user
    @Query("SELECT u.id FROM User u WHERE u.ownerId = :currentUserId")
    List<Long> findDirectSubsellers(@Param("currentUserId") Long currentUserId);

    // Recursive query to fetch all subsellers (including subsellers of subsellers)
    @Query(
            value = "WITH RECURSIVE SubsellerHierarchy AS (" +
                    "   SELECT u.id, u.owner_id FROM users u WHERE u.owner_id = :currentUserId " +  // Correct column name 'owner_id'
                    "   UNION ALL " +
                    "   SELECT u.id, u.owner_id FROM users u " +  // Correct column name 'owner_id'
                    "   JOIN SubsellerHierarchy sh ON u.owner_id = sh.id" +  // Correct column name 'owner_id'
                    ") " +
                    "SELECT id FROM SubsellerHierarchy",
            nativeQuery = true
    )
    List<Long> findAllSubsellers(@Param("currentUserId") Long currentUserId);
}
