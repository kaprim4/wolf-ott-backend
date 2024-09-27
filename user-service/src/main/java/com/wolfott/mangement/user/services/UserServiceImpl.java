package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.exceptions.UserNotFoundException;
import com.wolfott.mangement.user.mappers.UserMapper;
import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.repositories.UserRepository;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserCreateResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import com.wolfott.mangement.user.responses.UserUpdateResponse;
import com.wolfott.mangement.user.specifications.UserSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserSpecification userSpecification;
    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetailResponse getOne(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User Not Found"));
        return userMapper.toDetailResponse(user);
    }

    @Override
    public Page<UserCompactResponse> getAll(Map<String, Object> filters, Pageable pageable) {
        Specification<User> spec = userSpecification.dynamic(filters);
        Page<User> page = userRepository.findAll(spec, pageable);
        return userMapper.toCompactResponse(page);
    }

    @Override
    public Page<UserCompactResponse> getAll(String search, Pageable pageable) {
        Specification<User> spec = userSpecification.search(search);
        Page<User> page = userRepository.findAll(spec, pageable);
        return userMapper.toCompactResponse(page);
    }

    @Override
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }

    @Override
    public UserCreateResponse create(UserCreateRequest request) {
        User user = userMapper.toUser(request);
        user = userRepository.save(user);
        return userMapper.toCreateResponse(user);
    }

    @Override
    public UserUpdateResponse update(Long id, UserUpdateRequest request) {
        User user = userMapper.toUser(request);
        user.setId(id);
        user = userRepository.save(user);
        return userMapper.toUpdateResponse(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public String findById(Long memberId) {
        User user = userRepository.findById(memberId).orElse(null);
        return user != null ? user.getUsername() : null;
    }
}
