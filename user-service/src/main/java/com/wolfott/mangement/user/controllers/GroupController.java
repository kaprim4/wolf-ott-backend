package com.wolfott.mangement.user.controllers;

import com.wolfott.mangement.user.requests.GroupCreateRequest;
import com.wolfott.mangement.user.requests.GroupUpdateRequest;
import com.wolfott.mangement.user.responses.GroupCompactResponse;
import com.wolfott.mangement.user.responses.GroupCreateResponse;
import com.wolfott.mangement.user.responses.GroupDetailResponse;
import com.wolfott.mangement.user.responses.GroupUpdateResponse;
import com.wolfott.mangement.user.services.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {

    @Autowired
    GroupService groupService;

    @GetMapping("/{id}")
    public GroupDetailResponse getOne(@PathVariable("id") Long id){
        return groupService.getOne(id);
    }

    @GetMapping
    public Page<GroupCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return groupService.getAll(filters, pageable);
    }

    @GetMapping("/list")
    public List<GroupCompactResponse> getAll(@RequestParam Map<String, Object> filters){
        return groupService.getAll(filters);
    }

    @PostMapping
    public GroupCreateResponse create(@RequestBody GroupCreateRequest request){
        return groupService.create(request);
    }

    @PutMapping("/{id}")
    public GroupUpdateResponse update(@PathVariable("id") Long id, @RequestBody GroupUpdateRequest request){
        return groupService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id){
        groupService.delete(id);
    }
}
