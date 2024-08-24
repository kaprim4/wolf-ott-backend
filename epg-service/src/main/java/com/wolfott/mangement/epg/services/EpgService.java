package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.requests.EpgCreateRequest;
import com.wolfott.mangement.epg.requests.EpgUpdateRequest;
import com.wolfott.mangement.epg.responses.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface EpgService {
    EpgDetailResponse getOne(Long id);
    ChannelDetailResponse getChannel(Long epgId, Long channelId);
    DataDetailResponse getData(Long epgId, Long dataId);
    Page<EpgCompactResponse> getAll(Map<String, Object> filters, Pageable pageable);
    Page<ChannelCompactResponse> getChannels(Long epgId, Map<String, Object> filters, Pageable pageable);
    Page<DataCompactResponse> getData(Long epgId, Map<String, Object> filters, Pageable pageable);
    EpgCreateResponse create(EpgCreateRequest request);
    EpgUpdateResponse update(Long id, EpgUpdateRequest request);
    void delete(Long id);
}
