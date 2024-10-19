package com.wolfott.mangement.device.mappers;

import com.wolfott.mangement.device.models.MagClaim;
import com.wolfott.mangement.device.models.MagDevice;
import com.wolfott.mangement.device.models.MagEvent;
import com.wolfott.mangement.device.models.User;
import com.wolfott.mangement.device.requests.MagDeviceCreateRequest;
import com.wolfott.mangement.device.requests.MagDeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
public class MagMapper {
    private final ModelMapper modelMapper;
    @Autowired
    public MagMapper(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setAmbiguityIgnored(true);
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void setupMappings() {
        modelMapper.addMappings(new PropertyMap<MagDevice, MagDeviceCompactResponse>() {
            @Override
            protected void configure() {
                using(userToUsernameConverter()).map(source.getOwner(), destination.getOwner());
//                map().setTrial(source.);
            }
        });
    }

//    private MagDevice mapToDevice(Object request) {
//        MagDevice device = new MagDevice();
//        Class<?> requestClass = request.getClass();
//        Class<?> modelClass = MagDevice.class;
//
//        // Iterate over all fields in the Model class
//        for (Field modelField : modelClass.getDeclaredFields()) {
//            modelField.setAccessible(true); // Make private fields accessible
//
//            try {
//                // Find corresponding field in the request class
//                Field requestField = requestClass.getDeclaredField(modelField.getName());
//                requestField.setAccessible(true);
//
//                // Get the value from the request field
//                Object value = requestField.get(request);
//
//                // Set the value to the Model field
//                modelField.set(device, value);
//            } catch (NoSuchFieldException | IllegalAccessException e) {
//                // Handle exceptions if field does not exist or is inaccessible
//                e.printStackTrace();
//            }
//        }
//
//        return device;
//    }

    private Converter<User, String> userToUsernameConverter() {
        return context -> Optional.ofNullable(context.getSource())
                .map(User::getUsername)
                .orElse(null); // "Anonymous"
    }

    public MagDevice toDevice(MagDeviceCreateRequest request){
        return modelMapper.map(request, MagDevice.class);
    }

    public MagDevice toDevice(MagDeviceUpdateRequest request){
        return modelMapper.map(request, MagDevice.class);
    }

    public MagDeviceDetailResponse toDetailResponse(MagDevice device){
        return modelMapper.map(device, MagDeviceDetailResponse.class);
    }

    public MagDeviceCompactResponse toCompactResponse(MagDevice device){
        return modelMapper.map(device, MagDeviceCompactResponse.class);
    }

    public MagDeviceCreateResponse toCreateResponse(MagDevice device){
        return modelMapper.map(device, MagDeviceCreateResponse.class);
    }

    public MagDeviceUpdateResponse toUpdateResponse(MagDevice device){
        return modelMapper.map(device, MagDeviceUpdateResponse.class);
    }

    public Page<MagDeviceCompactResponse> toCompactResponse(Page<MagDevice> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    private MagClaim mapToClaim(Object request) {
        MagClaim claim = new MagClaim();
        Class<?> requestClass = request.getClass();
        Class<?> modelClass = MagClaim.class;

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
                modelField.set(claim, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions if field does not exist or is inaccessible
                e.printStackTrace();
            }
        }

        return claim;
    }

//    public MagClaim mapToClaim(MagClaimCreateRequest request){
//        return mapToClaim(request);
//    }

//    public MagClaim mapToClaim(MagClaimUpdateRequest request){
//        return mapToClaim(request);
//    }

    public MagClaimDetailResponse toDetailResponse(MagClaim device){
        return modelMapper.map(device, MagClaimDetailResponse.class);
    }

    public MagClaimCompactResponse toCompactResponse(MagClaim device){
        return modelMapper.map(device, MagClaimCompactResponse.class);
    }

    public MagClaimCreateResponse toCreateResponse(MagClaim device){
        return modelMapper.map(device, MagClaimCreateResponse.class);
    }

    public MagClaimUpdateResponse toUpdateResponse(MagClaim device){
        return modelMapper.map(device, MagClaimUpdateResponse.class);
    }

    public Page<MagClaimCompactResponse> toClaimCompactResponse(Page<MagClaim> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    private MagEvent mapToEvent(Object request) {
        MagEvent event = new MagEvent();
        Class<?> requestClass = request.getClass();
        Class<?> modelClass = MagClaim.class;

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
                modelField.set(event, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions if field does not exist or is inaccessible
                e.printStackTrace();
            }
        }

        return event;
    }

//    public MagEvent mapToEvent(MagEventCreateRequest request){
//        return mapToClaim(request);
//    }

//    public MagEvent mapToEvent(MagEventUpdateRequest request){
//        return mapToClaim(request);
//    }

    public MagEventDetailResponse toDetailResponse(MagEvent device){
        return modelMapper.map(device, MagEventDetailResponse.class);
    }

    public MagEventCompactResponse toCompactResponse(MagEvent device){
        return modelMapper.map(device, MagEventCompactResponse.class);
    }

    public MagEventCreateResponse toCreateResponse(MagEvent device){
        return modelMapper.map(device, MagEventCreateResponse.class);
    }

    public MagEventUpdateResponse toUpdateResponse(MagEvent device){
        return modelMapper.map(device, MagEventUpdateResponse.class);
    }

    public Page<MagEventCompactResponse> toEventCompactResponse(Page<MagEvent> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }
}
