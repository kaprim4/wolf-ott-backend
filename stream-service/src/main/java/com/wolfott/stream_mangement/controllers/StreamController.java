package com.wolfott.stream_mangement.controllers;

import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import com.wolfott.stream_mangement.services.StreamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/streams")
public class StreamController {

    @Autowired
    StreamService streamService;

    @GetMapping("/list")
    public List<StreamCompactResponse> getAll(@RequestParam Map<String, Object> filters){
        return streamService.getAll(filters);
    }

    @GetMapping
    public Page<StreamCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable){
        return streamService.getAll(filters, pageable);
    }

    @DeleteMapping("/{id}")
    public void delete(Long id){
        streamService.delete(id);
    }
}
