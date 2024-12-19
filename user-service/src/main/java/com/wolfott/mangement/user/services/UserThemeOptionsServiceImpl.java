package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.models.UserThemeOptions;
import com.wolfott.mangement.user.repositories.*;
import com.wolfott.mangement.user.requests.UserThemeOptionsRequest;
import com.wolfott.mangement.user.responses.UserDetailResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class UserThemeOptionsServiceImpl implements UserThemeOptionsService {

    @Autowired
    private UserThemeOptionsRepository userThemeOptionsRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserThemeOptions> getThemeOptions(Long id) {
        return userThemeOptionsRepository.findByUserId(id);
    }

    @Override
    public UserThemeOptions create(UserThemeOptionsRequest request) {
        Optional<UserThemeOptions> userThemeOptions = userThemeOptionsRepository.findByUserId(request.getUserId());
        log.info("request: {}", request);
        if (userThemeOptions.isEmpty()) {
            userThemeOptions = Optional.of(UserThemeOptions.builder()
                    .theme(request.getTheme())
                    .activeTheme(request.getActiveTheme())
                    .language(request.getLanguage())
                    .userId(request.getUserId())
                    .build());
            log.info("userThemeOptions: {}", userThemeOptions.get().getTheme());
            userThemeOptionsRepository.save(userThemeOptions.get());

            User user = userService.findUserById(request.getUserId());
            user.setUserThemeOptionsId(userThemeOptions.get().getId());
            userRepository.save(user);
        }
        return userThemeOptions.get();
    }

    @Override
    public UserThemeOptions update(Long id, UserThemeOptionsRequest request) {
        Optional<UserThemeOptions> userTheme = userThemeOptionsRepository.findByUserId(id);
        log.info("request : {}", request);
        if (userTheme.isPresent()) {
            log.info("userTheme: {}", userTheme.get().getActiveTheme());
            UserThemeOptions userThemeOptions = userTheme.get();
            userThemeOptions.setActiveTheme(request.getActiveTheme());
            userThemeOptions.setTheme(request.getTheme());
            userThemeOptions.setLanguage(request.getLanguage());
            userThemeOptionsRepository.save(userThemeOptions);
            log.info("userTheme after updated : {}", userThemeOptions.getActiveTheme());

            User user = userService.findUserById(request.getUserId());
            user.setUserThemeOptionsId(userThemeOptions.getId());
            userRepository.save(user);

            return userThemeOptions;
        }else{
            log.error("userTheme didn't exists.");
        }
        return null;
    }
}
