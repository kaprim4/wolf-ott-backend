package com.wolfott.mangement.device.mappers;

import com.wolfott.mangement.device.models.Enigma2Action;
import com.wolfott.mangement.device.models.Enigma2Device;
import com.wolfott.mangement.device.requests.Enigma2DeviceCreateRequest;
import com.wolfott.mangement.device.requests.Enigma2DeviceUpdateRequest;
import com.wolfott.mangement.device.responses.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.stream.Collectors;

@Component
public class Enigma2Mapper {
    @Autowired
    ModelMapper mapper;

    private Enigma2Device mapToDevice(Object request) {
        Enigma2Device device = new Enigma2Device();
        Class<?> requestClass = request.getClass();
        Class<?> modelClass = Enigma2Device.class;

        // Iterate over all fields in the Profile class
        for (Field modelField : modelClass.getDeclaredFields()) {
            modelField.setAccessible(true); // Make private fields accessible

            try {
                // Find corresponding field in the request class
                Field requestField = requestClass.getDeclaredField(modelField.getName());
                requestField.setAccessible(true);

                // Get the value from the request field
                Object value = requestField.get(request);

                // Set the value to the Model field
                modelField.set(device, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions if field does not exist or is inaccessible
                e.printStackTrace();
            }
        }

        return device;
    }

    public Enigma2Device toDevice(Enigma2DeviceCreateRequest request){
        return mapToDevice(request);
    }

    public Enigma2Device toDevice(Enigma2DeviceUpdateRequest request){
        return mapToDevice(request);
    }

    public Enigma2DeviceDetailResponse toDetailResponse(Enigma2Device device){
        return mapper.map(device, Enigma2DeviceDetailResponse.class);
    }

    public Enigma2DeviceCompactResponse toCompactResponse(Enigma2Device device){
        return mapper.map(device, Enigma2DeviceCompactResponse.class);
    }

    public Enigma2DeviceCreateResponse toCreateResponse(Enigma2Device device){
        return mapper.map(device, Enigma2DeviceCreateResponse.class);
    }

    public Enigma2DeviceUpdateResponse toUpdateResponse(Enigma2Device device){
        return mapper.map(device, Enigma2DeviceUpdateResponse.class);
    }

    public Page<Enigma2DeviceCompactResponse> toCompactResponse(Page<Enigma2Device> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }

    private Enigma2Action mapToAction(Object request) {
        Enigma2Action action = new Enigma2Action();
        Class<?> requestClass = request.getClass();
        Class<?> modelClass = Enigma2Action.class;

        // Iterate over all fields in the Profile class
        for (Field modelField : modelClass.getDeclaredFields()) {
            modelField.setAccessible(true); // Make private fields accessible

            try {
                // Find corresponding field in the request class
                Field requestField = requestClass.getDeclaredField(modelField.getName());
                requestField.setAccessible(true);

                // Get the value from the request field
                Object value = requestField.get(request);

                // Set the value to the Model field
                modelField.set(action, value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                // Handle exceptions if field does not exist or is inaccessible
                e.printStackTrace();
            }
        }

        return action;
    }

//    public Enigma2Action mapToAction(Enigma2ActionCreateRequest request){
//        return mapToDevice(request);
//    }

//    public Enigma2Action mapToAction(Enigma2ActionUpdateRequest request){
//        return mapToDevice(request);
//    }

    public Enigma2ActionDetailResponse toDetailResponse(Enigma2Action device){
        return mapper.map(device, Enigma2ActionDetailResponse.class);
    }

    public Enigma2ActionCompactResponse toCompactResponse(Enigma2Action device){
        return mapper.map(device, Enigma2ActionCompactResponse.class);
    }

    public Enigma2ActionCreateResponse toCreateResponse(Enigma2Action device){
        return mapper.map(device, Enigma2ActionCreateResponse.class);
    }

    public Enigma2ActionUpdateResponse toUpdateResponse(Enigma2Action device){
        return mapper.map(device, Enigma2ActionUpdateResponse.class);
    }

    public Page<Enigma2ActionCompactResponse> toActionCompactResponse(Page<Enigma2Action> page) {
        return new PageImpl<>(
                page.getContent().stream()
                        .map(this::toCompactResponse)
                        .collect(Collectors.toList()),
                page.getPageable(),
                page.getTotalElements()
        );
    }
}
