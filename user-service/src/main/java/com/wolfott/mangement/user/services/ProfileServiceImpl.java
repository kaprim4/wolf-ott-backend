package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.exceptions.ProfileNotFoundException;
import com.wolfott.mangement.user.mappers.ProfileMapper;
import com.wolfott.mangement.user.models.Profile;
import com.wolfott.mangement.user.repositories.ProfileRepository;
import com.wolfott.mangement.user.requests.ProfileCreateRequest;
import com.wolfott.mangement.user.requests.ProfileUpdateRequest;
import com.wolfott.mangement.user.responses.ProfileCompactResponse;
import com.wolfott.mangement.user.responses.ProfileCreateResponse;
import com.wolfott.mangement.user.responses.ProfileDetailResponse;
import com.wolfott.mangement.user.responses.ProfileUpdateResponse;
import com.wolfott.mangement.user.specifications.ProfileSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    ProfileSpecification profileSpecification;
    @Autowired
    ProfileMapper profileMapper;

    @Override
    public ProfileDetailResponse getOne(Long id) {
        Profile profile = profileRepository.findById(id).orElseThrow(() -> new ProfileNotFoundException("Profile Not Found"));
        return profileMapper.toDetailResponse(profile);
    }

    @Override
    public Page<ProfileCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<Profile> spec = profileSpecification.dynamic(filters);
        Page<Profile> page = profileRepository.findAll(spec, pageable);
        return profileMapper.toCompactResponse(page);
    }

    @Override
    public ProfileCreateResponse create(ProfileCreateRequest request) {
        Profile profile = profileMapper.toProfile(request);
        profile = profileRepository.save(profile);
        return profileMapper.toCreateResponse(profile);
    }

    @Override
    public ProfileUpdateResponse update(Long id, ProfileUpdateRequest request) {
        Profile profile = profileMapper.toProfile(request);
        profile.setProfileId(id);
        profile = profileRepository.save(profile);
        return profileMapper.toUpdateResponse(profile);
    }

    @Override
    public void delete(Long id) {
        profileRepository.deleteById(id);
    }
}
