package com.wolfott.mangement.user.services;

import com.wolfott.mangement.user.models.User;
import com.wolfott.mangement.user.models.UserThemeOptions;
import com.wolfott.mangement.user.repositories.*;
import com.wolfott.mangement.user.requests.UserThemeOptionsRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserThemeOptionsServiceImpl implements UserThemeOptionsService {

    @Autowired
    private UserThemeOptionsRepository userThemeOptionsRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<UserThemeOptions> getThemeOptions(Long id) {
        return userThemeOptionsRepository.findByUser_Id(id);
    }

    @Override
    public UserThemeOptions create(UserThemeOptionsRequest request) {
        Optional<UserThemeOptions> userTheme = userThemeOptionsRepository.findByUser_Id(request.getUser_id());
        if(userTheme.isEmpty()) {
            User user = userRepository.findOneById(request.getUser_id());
            UserThemeOptions userThemeOptions = UserThemeOptions.builder()
                    .theme(request.getTheme())
                    .activeTheme(request.getActiveTheme())
                    .language(request.getLanguage())
                    .user(user)
                    .build();
            userThemeOptionsRepository.save(userThemeOptions);
            return userThemeOptions;
        }
        return userTheme.get();
    }

    @Override
    public UserThemeOptions update(UserThemeOptionsRequest request) {
        Optional<UserThemeOptions> userThemeOptions = userThemeOptionsRepository.findByUser_Id(request.getUser_id());
        if(userThemeOptions.isPresent()) {
            UserThemeOptions userThemeOptions1 = userThemeOptions.get();
            userThemeOptions1.setTheme(request.getTheme());
            userThemeOptions1.setActiveTheme(request.getActiveTheme());
            userThemeOptions1.setLanguage(request.getLanguage());
            userThemeOptionsRepository.save(userThemeOptions1);
        }
        return null;
    }
}
