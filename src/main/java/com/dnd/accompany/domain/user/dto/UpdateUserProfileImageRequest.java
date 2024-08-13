package com.dnd.accompany.domain.user.dto;

import java.util.List;

public record UpdateUserProfileImageRequest(
        List<String> imageUrls
) {
}
