package com.dnd.accompany.domain.auth.api;

import com.dnd.accompany.domain.auth.dto.AuthUserInfo;
import com.dnd.accompany.domain.auth.dto.DeleteUserRequest;
import com.dnd.accompany.domain.auth.dto.Tokens;
import com.dnd.accompany.domain.auth.dto.jwt.JwtAuthentication;
import com.dnd.accompany.domain.auth.oauth.dto.LoginRequest;
import com.dnd.accompany.domain.auth.oauth.dto.OAuthUserDataResponse;
import com.dnd.accompany.domain.auth.oauth.dto.OAuthUserInfo;
import com.dnd.accompany.domain.auth.oauth.service.OAuthService;
import com.dnd.accompany.domain.auth.service.TokenService;
import com.dnd.accompany.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Auth")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final TokenService tokenService;
    private final OAuthService oAuthService;
    private final UserService userService;

    @Operation(summary = "로그인")
    @PostMapping("/sign-in")
    public ResponseEntity<Tokens> signIn(@RequestBody LoginRequest loginRequest) {
        OAuthUserDataResponse oAuthUserData = oAuthService.login(loginRequest);

        OAuthUserInfo oAuthUserInfo = OAuthUserInfo.from(oAuthUserData);

        AuthUserInfo authUserInfo = userService.getOrRegister(oAuthUserInfo);

        Tokens tokens = tokenService.createTokens(authUserInfo);

        return ResponseEntity.ok(tokens);
    }

    @Operation(summary = "회원 탈퇴")
    @PostMapping("/withdraw")
    public ResponseEntity<Long> withdraw(
            @AuthenticationPrincipal JwtAuthentication user,
            @RequestBody DeleteUserRequest deleteUserRequest
    ) {
        Long userId = user.getId();
        oAuthService.revoke(userId);
        userService.delete(userId, deleteUserRequest);

        return ResponseEntity.ok(userId);
    }
}
