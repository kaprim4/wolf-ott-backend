package com.wolfott.mangement.line.configs;

import com.wolfott.mangement.line.commons.ContextHolder;
import com.wolfott.mangement.line.models.Line;
import com.wolfott.mangement.line.models.Bouquet;
import com.wolfott.mangement.line.requests.LineUpdateRequest;
import com.wolfott.mangement.line.requests.BouquetUpdateRequest;
import com.wolfott.mangement.line.repositories.LineRepository;
import com.wolfott.mangement.line.repositories.BouquetRepository;
import com.wolfott.mangement.line.responses.LineCompactResponse;
import org.modelmapper.*;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper(LineRepository lineRepository, BouquetRepository bouquetRepository) {
        ModelMapper modelMapper = new ModelMapper();
        // Configure mappings for Line
//        configureLineMappings(modelMapper, lineRepository);
        // Configure mappings for Bouquet
//        configureBouquetMappings(modelMapper, bouquetRepository);
        // Configure custom converters for Page objects
//        configurePageConverters(modelMapper); // , Line.class, LineCompactResponse.class

        return modelMapper;
    }

    private void configurePageConverters(ModelMapper modelMapper) {
        modelMapper.addConverter(new Converter<Page<?>, Page<?>>() {
            @Override
            public Page<?> convert(MappingContext<Page<?>, Page<?>> context) {
                Page<?> sourcePage = context.getSource();
                Pageable pageable = sourcePage.getPageable();
                long totalElements = sourcePage.getTotalElements();

                // Determine the source and destination types
                Class<?> sourceType = context.getSourceType().getComponentType();
                Class<?> destinationType = context.getDestinationType().getComponentType();

                // Convert entities to DTOs
                List<?> dtoList = sourcePage.getContent().stream()
                        .map(entity -> modelMapper.map(entity, destinationType))
                        .collect(Collectors.toList());

                return new PageImpl<>(dtoList, pageable, totalElements);
            }
        });
    }

    private <M, R> void configurePageConverters(ModelMapper modelMapper, Class<M> entityClass, Class<R> dtoClass) {
        modelMapper.addConverter(new Converter<Page<M>, Page<R>>() {
            @Override
            public Page<R> convert(MappingContext<Page<M>, Page<R>> context) {
                Page<M> sourcePage = context.getSource();
                Pageable pageable = sourcePage.getPageable();
                long totalElements = sourcePage.getTotalElements();

                // Convert entities to DTOs
                List<R> dtoList = sourcePage.getContent().stream()
                        .map(entity -> modelMapper.map(entity, dtoClass))
                        .collect(Collectors.toList());

                return new PageImpl<>(dtoList, pageable, totalElements);
            }
        });
    }

    private void configureLineMappings(ModelMapper modelMapper, LineRepository lineRepository) {
        modelMapper.createTypeMap(LineUpdateRequest.class, Line.class)
                .setProvider(request -> {
                    Long id = ContextHolder.getCurrentId(Long.class);
                    return lineRepository.findById(id).orElse(new Line());
                })
                .addMappings(new PropertyMap<LineUpdateRequest, Line>() {
                    @Override
                    protected void configure() {
                        map().setId(ContextHolder.getCurrentId(Long.class)); // Map the ID from context
                    }
                });
    }

    private void configureBouquetMappings(ModelMapper modelMapper, BouquetRepository bouquetRepository) {
        Provider<Bouquet> bouquetProvider = request -> {
            Long id = ContextHolder.getCurrentId(Long.class);
            return bouquetRepository.findById(id).orElse(new Bouquet());
        };
        PropertyMap<BouquetUpdateRequest, Bouquet> bouquetTypeMap = new PropertyMap<BouquetUpdateRequest, Bouquet>() {
            @Override
            protected void configure() {
                map().setId(ContextHolder.getCurrentId(Long.class)); // Map the ID from context
            }
        };
        modelMapper.createTypeMap(BouquetUpdateRequest.class, Bouquet.class).setProvider(bouquetProvider).addMappings(bouquetTypeMap);
    }
}
