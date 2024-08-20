package com.wolfott.mangement.user.configs;

import com.wolfott.mangement.user.repositories.GroupRepository;
import com.wolfott.mangement.user.repositories.PackageRepository;
import com.wolfott.mangement.user.repositories.ProfileRepository;
import com.wolfott.mangement.user.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    @Bean
    public ModelMapper modelMapper(UserRepository userRepository, GroupRepository groupRepository, PackageRepository packageRepository, ProfileRepository profileRepository) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }
}
