package com.wolfott.mangement.epg.mappers;

import com.wolfott.mangement.epg.models.Epg;
import com.wolfott.mangement.epg.models.EpgChannel;
import com.wolfott.mangement.epg.models.EpgData;
import com.wolfott.mangement.epg.requests.EpgCreateRequest;
import com.wolfott.mangement.epg.requests.EpgUpdateRequest;
import com.wolfott.mangement.epg.responses.*;
import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class EpgMapper {
    @Autowired
    ModelMapper mapper;
    public Epg toEpg(EpgCreateRequest request){
        return mapper.map(request, Epg.class);
    }
    public Epg toEpg(EpgUpdateRequest request){
        return mapper.map(request, Epg.class);
    }

    public EpgCreateResponse toCreateResponse(Epg model){
        return mapper.map(model, EpgCreateResponse.class);
    }

    public EpgUpdateResponse toUpdateResponse(Epg model){
        return mapper.map(model, EpgUpdateResponse.class);
    }

    public EpgDetailResponse toDetailResponse(Epg model){
        return mapper.map(model, EpgDetailResponse.class);
    }

    public EpgCompactResponse toCompactResponse(Epg model){
        return mapper.map(model, EpgCompactResponse.class);
    }

    public Page<EpgCompactResponse> toCompactResponse(Page<Epg> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    public Collection<EpgCompactResponse> toCompactResponse(Collection<Epg> collection) {
        return collection.stream().map(this::toCompactResponse).collect(Collectors.toList());
    }

//    CHANNEL SECTION

    private EpgChannel mapToChannel(Object request) {
        EpgChannel channel = new EpgChannel();
        Class<?> requestClass = request.getClass();
        Class<?> modelClass = EpgChannel.class;

        // Iterate over all fields in the Model class
        for (Field modelField : modelClass.getDeclaredFields()) {
            modelField.setAccessible(true); // Make private fields accessible

            try {
                // Find corresponding field in the request class
                Field requestField = requestClass.getDeclaredField(modelField.getName());
                requestField.setAccessible(true);

                // Get the value from the request field
                Object value = requestField.get(request);

                // Set the value to the Model field
                modelField.set(channel, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions if field does not exist or is inaccessible
                e.printStackTrace();
            }
        }

        return channel;
    }

//    public EpgChannel mapToChannel(ChannelCreateRequest request){
//        return mapToDevice(request);
//    }

//    public EpgChannel mapToChannel(ChannelUpdateRequest request){
//        return mapToDevice(request);
//    }

    public ChannelDetailResponse toDetailResponse(EpgChannel channel){
        return mapper.map(channel, ChannelDetailResponse.class);
    }

    public ChannelCompactResponse toCompactResponse(EpgChannel channel){
        return mapper.map(channel, ChannelCompactResponse.class);
    }

    public ChannelCreateResponse toCreateResponse(EpgChannel channel){
        return mapper.map(channel, ChannelCreateResponse.class);
    }

    public ChannelUpdateResponse toUpdateResponse(EpgChannel channel){
        return mapper.map(channel, ChannelUpdateResponse.class);
    }

    public Page<ChannelCompactResponse> toChannelCompactResponse(Page<EpgChannel> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    //    DATA SECTION

    private EpgData mapToData(Object request) {
        EpgData data = new EpgData();
        Class<?> requestClass = request.getClass();
        Class<?> modelClass = EpgData.class;

        // Iterate over all fields in the Model class
        for (Field modelField : modelClass.getDeclaredFields()) {
            modelField.setAccessible(true); // Make private fields accessible

            try {
                // Find corresponding field in the request class
                Field requestField = requestClass.getDeclaredField(modelField.getName());
                requestField.setAccessible(true);

                // Get the value from the request field
                Object value = requestField.get(request);

                // Set the value to the Model field
                modelField.set(data, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions if field does not exist or is inaccessible
                e.printStackTrace();
            }
        }

        return data;
    }

//    public EpgData mapToData(DataCreateRequest request){
//        return mapToData(request);
//    }

//    public EpgData mapToData(DataUpdateRequest request){
//        return mapToData(request);
//    }

    public DataDetailResponse toDetailResponse(EpgData data){
        return mapper.map(data, DataDetailResponse.class);
    }

    public DataCompactResponse toCompactResponse(EpgData data){
        return mapper.map(data, DataCompactResponse.class);
    }

    public DataCreateResponse toCreateResponse(EpgData data){
        return mapper.map(data, DataCreateResponse.class);
    }

    public DataUpdateResponse toUpdateResponse(EpgData data){
        return mapper.map(data, DataUpdateResponse.class);
    }

    public Page<DataCompactResponse> toDataCompactResponse(Page<EpgData> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

}
