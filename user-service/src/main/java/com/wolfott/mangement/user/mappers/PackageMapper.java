package com.wolfott.mangement.user.mappers;

import com.wolfott.mangement.user.models.UserPackage;
import com.wolfott.mangement.user.requests.PackageCreateRequest;
import com.wolfott.mangement.user.requests.PackageUpdateRequest;
import com.wolfott.mangement.user.responses.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class PackageMapper {
    @Autowired
    ModelMapper mapper;
    public UserPackage toPackage(PackageCreateRequest request){
        return mapper.map(request, UserPackage.class);
    }
    public UserPackage toPackage(PackageUpdateRequest request){
        return mapper.map(request, UserPackage.class);
    }

    public PackageCreateResponse toCreateResponse(UserPackage model){
        return mapper.map(model, PackageCreateResponse.class);
    }

    public PackageUpdateResponse toUpdateResponse(UserPackage model){
        return mapper.map(model, PackageUpdateResponse.class);
    }

    public PackageDetailResponse toDetailResponse(UserPackage model){
        return mapper.map(model, PackageDetailResponse.class);
    }

    public PackageCompactResponse toCompactResponse(UserPackage model){
        return mapper.map(model, PackageCompactResponse.class);
    }

    public Page<PackageCompactResponse> toCompactResponse(Page<UserPackage> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<PackageCompactResponse> toCompactResponse(Collection<UserPackage> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
