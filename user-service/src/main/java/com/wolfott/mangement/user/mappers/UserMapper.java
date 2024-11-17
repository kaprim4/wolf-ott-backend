package com.wolfott.mangement.user.mappers;

import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserCreateResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import com.wolfott.mangement.user.responses.UserUpdateResponse;
import jakarta.annotation.PostConstruct;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    @Autowired
    ModelMapper modelMapper;

    @PostConstruct
    private void setupMappings() {
        modelMapper.addMappings(new PropertyMap<User, UserCompactResponse>() {
            @Override
            protected void configure() {
                using(userToUsernameConverter()).map(source.getOwner(), destination.getOwner());
            }
        });

        modelMapper.addMappings(new PropertyMap<User, UserDetailResponse>() {
            @Override
            protected void configure() {
//                map().setId(destination.getId());
                map(source.getId(), destination.getId());
//                map().setOwnerId(destination.getOwnerId());
                map(source.getOwnerId(), destination.getOwnerId());
            }
        });

    }

    private Converter<User, String> userToUsernameConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(User::getUsername)
                .orElse(null); // "Anonymous"
    }
    public User toUser(UserCreateRequest request){
        return modelMapper.map(request, User.class);
    }
    public User toUser(UserUpdateRequest request){
        return modelMapper.map(request, User.class);
    }

    public User toUser(UserUpdateRequest request, User model){
        modelMapper.map(request, model);
        return model;
    }

    public UserCreateResponse toCreateResponse(User user){
        return modelMapper.map(user, UserCreateResponse.class);
    }

    public UserUpdateResponse toUpdateResponse(User user){
        return modelMapper.map(user, UserUpdateResponse.class);
    }

    public UserDetailResponse toDetailResponse(User user){
        return modelMapper.map(user, UserDetailResponse.class);
    }

    public UserCompactResponse toCompactResponse(User user){
        return modelMapper.map(user, UserCompactResponse.class);
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

    public List<UserCompactResponse> toCompactResponse(List<User> list) {
        return list.stream().map(this::toCompactResponse).toList();
    }

    public Collection<UserCompactResponse> toCompactResponse(Collection<User> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }
}
