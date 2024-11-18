package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.mappers.GroupMapper;
import com.wolfott.mangement.user.models.UserGroup;
import com.wolfott.mangement.user.repositories.GroupRepository;
import com.wolfott.mangement.user.requests.GroupCreateRequest;
import com.wolfott.mangement.user.requests.GroupUpdateRequest;
import com.wolfott.mangement.user.responses.GroupCompactResponse;
import com.wolfott.mangement.user.responses.GroupCreateResponse;
import com.wolfott.mangement.user.responses.GroupDetailResponse;
import com.wolfott.mangement.user.responses.GroupUpdateResponse;
import com.wolfott.mangement.user.specifications.GroupSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GroupServiceImpl implements GroupService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    GroupSpecification groupSpecification;
    @Autowired
    GroupMapper groupMapper;

    @Override
    public GroupDetailResponse getOne(Long id) {
        UserGroup group = groupRepository.findById(id).orElseThrow(() -> new RuntimeException("Group Not Found"));
        return groupMapper.toDetailResponse(group);
    }

    @Override
    public List<GroupCompactResponse> getAll(Map<String, Object> filters) {
        Specification<UserGroup> spec = groupSpecification.dynamic(filters);
        List<UserGroup> list = groupRepository.findAll(spec);
        return groupMapper.toCompactResponse(list);
    }

    @Override
    public Page<GroupCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<UserGroup> spec = groupSpecification.dynamic(filters);
        Page<UserGroup> page = groupRepository.findAll(spec, pageable);
        return groupMapper.toCompactResponse(page);
    }

    @Override
    public GroupCreateResponse create(GroupCreateRequest request) {
        UserGroup group = groupMapper.toGroup(request);
        group = groupRepository.save(group);
        return groupMapper.toCreateResponse(group);
    }

    @Override
    public GroupUpdateResponse update(Long id, GroupUpdateRequest request) {
        UserGroup group = groupMapper.toGroup(request);
        group.setGroupId(id);
        group = groupRepository.save(group);
        return groupMapper.toUpdateResponse(group);
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }
}
