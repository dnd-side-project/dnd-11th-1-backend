package com.dnd.accompany.domain.accompany.service;

import static com.dnd.accompany.domain.accompany.entity.enums.RequestState.*;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardThumbnail;
import com.dnd.accompany.domain.accompany.api.dto.AccompanyRequestDetailInfo;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyRequest;
import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyRequest;
import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyResponse;
import com.dnd.accompany.domain.accompany.api.dto.UserProfileDetailInfo;
import com.dnd.accompany.domain.accompany.entity.AccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.AccompanyRequest;
import com.dnd.accompany.domain.accompany.entity.enums.Role;
import com.dnd.accompany.domain.accompany.exception.accompanyboard.AccompanyBoardNotFoundException;
import com.dnd.accompany.domain.accompany.exception.accompanyrequest.AccompanyRequestNotFoundException;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyBoardRepository;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyRequestRepository;
import com.dnd.accompany.domain.user.dto.UserProfileDetailResponse;
import com.dnd.accompany.domain.user.entity.User;
import com.dnd.accompany.domain.user.service.UserProfileService;
import com.dnd.accompany.global.common.response.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyRequestService {
	private final AccompanyRequestRepository accompanyRequestRepository;
	private final AccompanyBoardService accompanyBoardService;
	private final UserProfileService userProfileService;

	@Transactional
	public void save(Long userId, CreateAccompanyRequest request) {
		accompanyRequestRepository.save(AccompanyRequest.builder()
			.user(User.builder().id(userId).build())
			.accompanyBoard(getAccompanyBoard(request.boardId()))
			.requestState(HOLDING)
			.introduce(request.introduce())
			.chatLink(request.chatLink())
			.build());
	}

	@Transactional(readOnly = true)
	public ReadAccompanyResponse read(ReadAccompanyRequest request) {
		AccompanyBoardThumbnail boardThumbnail = accompanyBoardService.findAccompanyThumbnail(request.boardId(), request.userId());

		UserProfileDetailInfo profileDetailInfo = getUserProfileDetailInfo(request.userId());

		AccompanyRequestDetailInfo requestDetailInfo = getAccompanyRequestDetailInfo(request.boardId(),
			request.userId(), request.role());

		return new ReadAccompanyResponse(boardThumbnail, profileDetailInfo, requestDetailInfo);
	}

	@Transactional
	public void deleteByBoardId(Long boardId) {
		accompanyRequestRepository.deleteByAccompanyBoardId(boardId);
	}

	private UserProfileDetailInfo getUserProfileDetailInfo(Long userId) {
		UserProfileDetailResponse response = userProfileService.findUserProfileDetails(userId);

		return UserProfileDetailInfo.builder()
			.userId(response.userId())
			.nickname(response.nickname())
			.profileImageUrl(response.profileImageUrl())
			.birthYear(response.birthYear())
			.gender(response.gender())
			.travelPreferences(response.travelPreferences())
			.travelStyles(response.travelStyles())
			.foodPreferences(response.foodPreferences())
			.userImageUrls(response.userImageUrls())
			.build();
	}

	private AccompanyRequestDetailInfo getAccompanyRequestDetailInfo(Long boardId, Long userId, Role role) {
		AccompanyRequest accompanyRequest = accompanyRequestRepository.findRequestDetailInfo(boardId, userId, role)
            .orElseThrow(() -> new AccompanyRequestNotFoundException(ErrorCode.ACCOMPANY_REQUEST_NOT_FOUND));

        return AccompanyRequestDetailInfo.builder()
            .boardId(accompanyRequest.getAccompanyBoard().getId())
			.userId(accompanyRequest.getUser().getId())
			.introduce(accompanyRequest.getIntroduce())
			.chatLink(accompanyRequest.getChatLink())
			.build();
	}

	public AccompanyBoard getAccompanyBoard(Long boardId) {
		return AccompanyBoard.builder().id(boardId).build();
	}
}
