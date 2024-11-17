package com.wolfott.mangement.user.controllers;

import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.models.UserThemeOptions;
import com.wolfott.mangement.user.requests.UserCreateRequest;
import com.wolfott.mangement.user.requests.UserThemeOptionsRequest;
import com.wolfott.mangement.user.requests.UserUpdateRequest;
import com.wolfott.mangement.user.responses.*;
import com.wolfott.mangement.user.services.UserLogService;
import com.wolfott.mangement.user.services.UserService;
import com.wolfott.mangement.user.services.UserThemeOptionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    private UserLogService userLogService;
    @Autowired
    private UserThemeOptionsService userThemeOptionsService;

    @GetMapping("/{id}")
    public UserDetailResponse getOne(@PathVariable("id") Long id) {
        return userService.getOne(id);
    }

    @GetMapping("/{id}/username")
    public String getUsernameByMemberId(@PathVariable("id") Long memberId) {
        return userService.findById(memberId);
    }

    @GetMapping
    public Page<UserCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return userService.getAll(filters, pageable);
    }

    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/list")
    public List<UserCompactResponse> getAll(@RequestParam Map<String, Object> filters) {
        return userService.getAll(filters);
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

    @GetMapping("/logs")
    public Page<UserLogCompactResponse> getAllLogs(Pageable pageable){
        return userLogService.getAll(pageable);
    }

    @GetMapping("/theme-options/{id}")
    public Optional<UserThemeOptions> getThemeOptions(@PathVariable("id") Long id){
        return userThemeOptionsService.getThemeOptions(id);
    }

    @PostMapping("/theme-options")
    public UserThemeOptions createUserThemeOptions(@RequestBody UserThemeOptionsRequest request) {
        return userThemeOptionsService.create(request);
    }

    @PutMapping("/theme-options")
    public UserThemeOptions updateUserThemeOptions(@RequestBody UserThemeOptionsRequest request) {
        return userThemeOptionsService.update(request);
    }

}
