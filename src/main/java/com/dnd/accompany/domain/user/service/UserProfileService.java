package com.dnd.accompany.domain.user.service;

import com.dnd.accompany.domain.user.dto.CreateUserProfileRequest;
import com.dnd.accompany.domain.user.entity.UserProfile;
import com.dnd.accompany.domain.user.infrastructure.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private final UserProfileRepository userProfileRepository;

    @Transactional
    public void createUserProfile(Long userId, CreateUserProfileRequest createUserProfileRequest) {
        UserProfile userProfile = UserProfile.builder()
                .userId(userId)
                .birthYear(createUserProfileRequest.birthYear())
                .gender(createUserProfileRequest.gender())
                .travelPreferences(createUserProfileRequest.travelPreferences())
                .travelStyles(createUserProfileRequest.travelStyles())
                .foodPreferences(createUserProfileRequest.foodPreferences())
                .build();

        userProfileRepository.save(userProfile);
    }

    @Transactional(readOnly = true)
    public boolean existByUserId(Long userId) {
        return userProfileRepository.existsById(userId);
    }
}
