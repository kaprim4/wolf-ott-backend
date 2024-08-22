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

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class ProfileMapper {
    @Autowired
    ModelMapper mapper;

    private Profile mapToProfile(Object request) {
        Profile profile = new Profile();
        Class<?> requestClass = request.getClass();
        Class<?> profileClass = Profile.class;

        // Iterate over all fields in the Profile class
        for (Field profileField : profileClass.getDeclaredFields()) {
            profileField.setAccessible(true); // Make private fields accessible

            try {
                // Find corresponding field in the request class
                Field requestField = requestClass.getDeclaredField(profileField.getName());
                requestField.setAccessible(true);

                // Get the value from the request field
                Object value = requestField.get(request);

                // Set the value to the Profile field
                profileField.set(profile, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions if field does not exist or is inaccessible
                e.printStackTrace();
            }
        }

        return profile;
    }

    public Profile toProfile(ProfileCreateRequest request){
        return mapToProfile(request);
    }
    public Profile toProfile(ProfileUpdateRequest request){
        return mapToProfile(request);
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
