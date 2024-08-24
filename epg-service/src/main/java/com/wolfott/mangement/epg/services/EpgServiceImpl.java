package com.wolfott.mangement.epg.services;

import com.wolfott.mangement.epg.exceptions.ChannelNotFoundException;
import com.wolfott.mangement.epg.exceptions.DataNotFoundException;
import com.wolfott.mangement.epg.exceptions.EpgNotFoundException;
import com.wolfott.mangement.epg.mappers.EpgMapper;
import com.wolfott.mangement.epg.models.Epg;
import com.wolfott.mangement.epg.models.EpgChannel;
import com.wolfott.mangement.epg.models.EpgData;
import com.wolfott.mangement.epg.repositories.ChannelRepository;
import com.wolfott.mangement.epg.repositories.DataRepository;
import com.wolfott.mangement.epg.repositories.EpgRepository;
import com.wolfott.mangement.epg.requests.EpgCreateRequest;
import com.wolfott.mangement.epg.requests.EpgUpdateRequest;
import com.wolfott.mangement.epg.responses.*;
import com.wolfott.mangement.epg.specifications.EpgSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class EpgServiceImpl implements EpgService {

    @Autowired
    EpgRepository epgRepository;
    @Autowired
    ChannelRepository channelRepository;
    @Autowired
    DataRepository dataRepository;
    @Autowired
    EpgSpecification epgSpecification;
    @Autowired
    EpgMapper epgMapper;

    @Override
    public EpgDetailResponse getOne(Long id) {
        Epg epg = epgRepository.findById(id).orElseThrow(() -> new EpgNotFoundException("EPG Not Found"));
        return epgMapper.toDetailResponse(epg);
    }

    @Override
    public ChannelDetailResponse getChannel(Long epgId, Long channelId) {
        EpgChannel channel = channelRepository.findByIdAndEpg_Id(channelId, epgId).orElseThrow(() -> new ChannelNotFoundException("Channel Not Found"));
        return epgMapper.toDetailResponse(channel);
//        throw new RuntimeException("UNDER CONSTRUCTION");
    }

    @Override
    public DataDetailResponse getData(Long epgId, Long dataId) {
        EpgData data = dataRepository.findByIdAndEpg_Id(dataId, epgId).orElseThrow(() -> new DataNotFoundException("Data Not Found"));
        return epgMapper.toDetailResponse(data);
//        throw new RuntimeException("UNDER CONSTRUCTION");
    }

    @Override
    public Page<EpgCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Epg> spec = epgSpecification.dynamic(filters);
        Page<Epg> page = epgRepository.findAll(spec, pageable);
        return epgMapper.toCompactResponse(page);
    }

    @Override
    public Page<ChannelCompactResponse> getChannels(Long epgId, Map<String, Object> filters, Pageable pageable) {
        Page<EpgChannel> page = channelRepository.findByEpg_Id(epgId, pageable);
        return epgMapper.toChannelCompactResponse(page);
//        throw new RuntimeException("UNDER CONSTRUCTION");
    }

    @Override
    public Page<DataCompactResponse> getData(Long epgId, Map<String, Object> filters, Pageable pageable) {
        Page<EpgData> page = dataRepository.findByEpg_Id(epgId, pageable);
        return epgMapper.toDataCompactResponse(page);
//        throw new RuntimeException("UNDER CONSTRUCTION");
    }

    @Override
    public EpgCreateResponse create(EpgCreateRequest request) {
        Epg epg = epgMapper.toEpg(request);
        epg = epgRepository.save(epg);
        return epgMapper.toCreateResponse(epg);
    }

    @Override
    public EpgUpdateResponse update(Long id, EpgUpdateRequest request) {
        Epg epg = epgMapper.toEpg(request);
        epg.setId(id);
        epg = epgRepository.save(epg);
        return epgMapper.toUpdateResponse(epg);
    }

    @Override
    public void delete(Long id) {
        epgRepository.deleteById(id);
    }
}
