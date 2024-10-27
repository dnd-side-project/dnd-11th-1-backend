package com.dnd.accompany.domain.user.api;

import com.dnd.accompany.domain.auth.dto.jwt.JwtAuthentication;
import com.dnd.accompany.domain.user.dto.CreateUserProfileRequest;
import com.dnd.accompany.domain.user.dto.CreateUserProfileResponse;
import com.dnd.accompany.domain.user.dto.UpdateUserProfileImageRequest;
import com.dnd.accompany.domain.user.dto.UpdateUserProfileRequest;
import com.dnd.accompany.domain.user.dto.UserProfileDetailResponse;
import com.dnd.accompany.domain.user.dto.UserProfileResponse;
import com.dnd.accompany.domain.user.service.UserProfileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "프로필")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/profiles")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Operation(summary = "온보딩 정보 저장")
    @PostMapping
    public ResponseEntity<CreateUserProfileResponse> createUserProfile(@AuthenticationPrincipal JwtAuthentication user,
                                                                       @RequestBody @Valid CreateUserProfileRequest createUserProfileRequest
    ) {
        userProfileService.createUserProfile(user.getId(), createUserProfileRequest);
        return ResponseEntity.ok(new CreateUserProfileResponse(user.getId()));
    }

    @Operation(summary = "온보딩 여부 조회")
    @GetMapping("/exist")
    public ResponseEntity<Boolean> existUserProfile(@AuthenticationPrincipal JwtAuthentication user) {
        boolean result = userProfileService.existByUserId(user.getId());
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "내 정보 수정")
    @PutMapping
    public ResponseEntity<Long> editProfile(@AuthenticationPrincipal JwtAuthentication user,
                                            @RequestBody @Valid UpdateUserProfileRequest updateUserProfileRequest) {

        Long userId = userProfileService.updateProfile(user.getId(), updateUserProfileRequest);
        return ResponseEntity.ok(userId);
    }

    @Operation(summary = "유저 이미지 수정(프로필 사진 X)")
    @PutMapping("/images")
    public ResponseEntity<Long> editProfileImages(@AuthenticationPrincipal JwtAuthentication user,
                                  @RequestBody @Valid UpdateUserProfileImageRequest updateUserProfileImageRequest) {

        Long userId = userProfileService.updateUserProfileImages(user.getId(), updateUserProfileImageRequest);
        return ResponseEntity.ok(userId);
    }

    @Operation(summary = "내 프로필 상세 조회")
    @GetMapping("/details")
    public ResponseEntity<UserProfileDetailResponse> getUserProfileDetails(@AuthenticationPrincipal JwtAuthentication user) {
        UserProfileDetailResponse userProfile = userProfileService.findUserProfileDetails(user.getId());
        return ResponseEntity.ok(userProfile);
    }

    @Operation(summary = "내 프로필 조회")
    @GetMapping
    public ResponseEntity<UserProfileResponse> getUserProfile(@AuthenticationPrincipal JwtAuthentication user) {
        UserProfileResponse userProfile = userProfileService.findUserProfile(user.getId());
        return ResponseEntity.ok(userProfile);
    }

    @Operation(summary = "유저 프로필 상세 조회")
    @GetMapping("/{userId}")
    public ResponseEntity<UserProfileDetailResponse> getUserProfileDetails(@PathVariable Long userId) {
        UserProfileDetailResponse userProfile = userProfileService.findUserProfileDetails(userId);
        return ResponseEntity.ok(userProfile);
    }
}
