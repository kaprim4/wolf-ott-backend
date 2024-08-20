package com.wolfott.mangement.user.mappers;

import com.wolfott.mangement.user.models.UserGroup;
import com.wolfott.mangement.user.requests.GroupCreateRequest;
import com.wolfott.mangement.user.requests.GroupUpdateRequest;
import com.wolfott.mangement.user.responses.GroupCompactResponse;
import com.wolfott.mangement.user.responses.GroupCreateResponse;
import com.wolfott.mangement.user.responses.GroupDetailResponse;
import com.wolfott.mangement.user.responses.GroupUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class GroupMapper {
    @Autowired
    ModelMapper mapper;
    public UserGroup toGroup(GroupCreateRequest request){
        return mapper.map(request, UserGroup.class);
    }
    public UserGroup toGroup(GroupUpdateRequest request){
        return mapper.map(request, UserGroup.class);
    }

    public GroupCreateResponse toCreateResponse(UserGroup group){
        return mapper.map(group, GroupCreateResponse.class);
    }

    public GroupUpdateResponse toUpdateResponse(UserGroup group){
        return mapper.map(group, GroupUpdateResponse.class);
    }

    public GroupDetailResponse toDetailResponse(UserGroup group){
        return mapper.map(group, GroupDetailResponse.class);
    }

    public GroupCompactResponse toCompactResponse(UserGroup group){
        return mapper.map(group, GroupCompactResponse.class);
    }

    public Page<GroupCompactResponse> toCompactResponse(Page<UserGroup> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<GroupCompactResponse> toCompactResponse(Collection<UserGroup> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
