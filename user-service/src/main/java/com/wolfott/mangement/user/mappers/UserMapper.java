package com.wolfott.mangement.user.mappers;

import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserCreateResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import com.wolfott.mangement.user.responses.UserUpdateResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    ModelMapper mapper;
    public User toUser(UserCreateRequest request){
        return mapper.map(request, User.class);
    }
    public User toUser(UserUpdateRequest request){
        return mapper.map(request, User.class);
    }

    public UserCreateResponse toCreateResponse(User user){
        return mapper.map(user, UserCreateResponse.class);
    }

    public UserUpdateResponse toUpdateResponse(User user){
        return mapper.map(user, UserUpdateResponse.class);
    }

    public UserDetailResponse toDetailResponse(User user){
        return mapper.map(user, UserDetailResponse.class);
    }

    public UserCompactResponse toCompactResponse(User user){
        return mapper.map(user, UserCompactResponse.class);
    }

    public Page<UserCompactResponse> toCompactResponse(Page<User> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<UserCompactResponse> toCompactResponse(Collection<User> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
