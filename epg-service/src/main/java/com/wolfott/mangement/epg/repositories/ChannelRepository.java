package com.wolfott.mangement.epg.repositories;

import com.wolfott.mangement.epg.models.EpgChannel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ChannelRepository extends JpaRepository<EpgChannel, Long>, JpaSpecificationExecutor<EpgChannel> {
}
