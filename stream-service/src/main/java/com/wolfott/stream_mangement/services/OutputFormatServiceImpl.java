package com.wolfott.stream_mangement.services;

import com.wolfott.stream_mangement.repositories.OutputFormatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OutputFormatServiceImpl implements OutputFormatService {

    @Autowired
    private OutputFormatRepository outputFormatRepository;

}
