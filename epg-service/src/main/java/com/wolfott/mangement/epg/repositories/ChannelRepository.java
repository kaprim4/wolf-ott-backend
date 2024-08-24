package com.wolfott.mangement.epg.repositories;

import com.wolfott.mangement.epg.models.EpgChannel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface ChannelRepository extends JpaRepository<EpgChannel, Long>, JpaSpecificationExecutor<EpgChannel> {
    Optional<EpgChannel> findByIdAndEpg_Id(Long channelId, Long epgId);
    Page<EpgChannel> findByEpg_Id(Long epgId, Pageable pageable);
    Page<EpgChannel> findByEpg_Id(Long epgId, Specification<EpgChannel> spec, Pageable pageable);
}
