package com.wolfott.stream_mangement.configs;

import com.wolfott.stream_mangement.repositories.CategoryRepository;
import com.wolfott.stream_mangement.repositories.StreamRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(CategoryRepository categoryRepository, StreamRepository streamRepository) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
