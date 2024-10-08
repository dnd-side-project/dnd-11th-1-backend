package com.dnd.accompany.domain.auth.oauth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OAuthUserDataResponse {
	private String provider;
	private String oauthId;
	private String profileImageUrl;
	private String nickname;
}
