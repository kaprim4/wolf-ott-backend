package com.wolfott.mangement.user.controllers;

import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.UserCompactResponse;
import com.wolfott.mangement.user.responses.UserCreateResponse;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import com.wolfott.mangement.user.responses.UserUpdateResponse;
import com.wolfott.mangement.user.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public UserDetailResponse getOne(@PathVariable("id") Long id) {
        return userService.getOne(id);
    }

    @GetMapping
    public Page<UserCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return userService.getAll(filters, pageable);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/search")
    public Page<UserCompactResponse> getAll(@RequestParam("q") String search, Pageable pageable) {
        return userService.getAll(search, pageable);
    }

    @PostMapping
    public UserCreateResponse create(@RequestBody UserCreateRequest request) {
        return userService.create(request);
    }

    @PutMapping("/{id}")
    public UserUpdateResponse update(@PathVariable("id") Long id, @RequestBody UserUpdateRequest request) {
        return userService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        userService.delete(id);
    }

    @GetMapping("/username")
    public String getUsernameByMemberId(@RequestParam Long memberId) {
        return userService.findById(memberId);
    }
}
