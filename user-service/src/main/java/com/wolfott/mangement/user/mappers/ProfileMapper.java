package com.wolfott.mangement.user.mappers;

import com.wolfott.mangement.user.models.Profile;

import com.wolfott.mangement.user.requests.ProfileCreateRequest;
import com.wolfott.mangement.user.requests.ProfileUpdateRequest;
import com.wolfott.mangement.user.responses.ProfileCompactResponse;
import com.wolfott.mangement.user.responses.ProfileCreateResponse;
import com.wolfott.mangement.user.responses.ProfileDetailResponse;
import com.wolfott.mangement.user.responses.ProfileUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ProfileMapper {
    @Autowired
    ModelMapper mapper;
    public Profile toProfile(ProfileCreateRequest request){
        return mapper.map(request, Profile.class);
    }
    public Profile toProfile(ProfileUpdateRequest request){
        return mapper.map(request, Profile.class);
    }

    public ProfileCreateResponse toCreateResponse(Profile model){
        return mapper.map(model, ProfileCreateResponse.class);
    }

    public ProfileUpdateResponse toUpdateResponse(Profile model){
        return mapper.map(model, ProfileUpdateResponse.class);
    }

    public ProfileDetailResponse toDetailResponse(Profile model){
        return mapper.map(model, ProfileDetailResponse.class);
    }

    public ProfileCompactResponse toCompactResponse(Profile model){
        return mapper.map(model, ProfileCompactResponse.class);
    }

    public Page<ProfileCompactResponse> toCompactResponse(Page<Profile> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<ProfileCompactResponse> toCompactResponse(Collection<Profile> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
