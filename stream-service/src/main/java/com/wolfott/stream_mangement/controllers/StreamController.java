package com.wolfott.stream_mangement.controllers;

import com.wolfott.stream_mangement.models.StreamType;
import com.wolfott.stream_mangement.responses.StreamCompactResponse;
import com.wolfott.stream_mangement.responses.StreamDetailResponse;
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

    @GetMapping("/{id}")
    public StreamDetailResponse getOne(@PathVariable("id") Long id) {
        return streamService.getOne(id);
    }

    @GetMapping("/list")
    public List<StreamCompactResponse> getAll(@RequestParam("type") String type) {
        return streamService.getAll();
    }

    @GetMapping
    public Page<StreamCompactResponse> getAll(@RequestParam Map<String, Object> filters, Pageable pageable) {
        return streamService.getAll(filters, pageable);
    }

    @GetMapping("/types")
    public List<StreamType> getTypes(){
        return streamService.getTypes();
    }

    @GetMapping("/types/{type}")
    public Page<StreamCompactResponse> getTypes(@PathVariable("type") String type, Pageable pageable){
        return streamService.getAllByType(type, pageable);
    }
}
