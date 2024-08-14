package com.dnd.accompany.domain.accompany.service;

import static com.dnd.accompany.domain.accompany.entity.AccompanyBoard.*;
import static com.dnd.accompany.domain.accompany.entity.enums.Role.*;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardDetailInfo;
import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardThumbnail;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardRequest;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardResponse;
import com.dnd.accompany.domain.accompany.api.dto.DetailInfo;
import com.dnd.accompany.domain.accompany.api.dto.FindBoardThumbnailsResult;
import com.dnd.accompany.domain.accompany.api.dto.FindDetailInfoResult;
import com.dnd.accompany.domain.accompany.api.dto.PageResponse;
import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyBoardResponse;
import com.dnd.accompany.domain.accompany.api.dto.UserProfileDetailInfo;
import com.dnd.accompany.domain.accompany.entity.AccompanyBoard;
import com.dnd.accompany.domain.accompany.entity.enums.Region;
import com.dnd.accompany.domain.accompany.exception.AccompanyBoardAccessDeniedException;
import com.dnd.accompany.domain.accompany.exception.AccompanyBoardNotFoundException;
import com.dnd.accompany.domain.accompany.infrastructure.AccompanyBoardRepository;
import com.dnd.accompany.global.common.response.ErrorCode;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AccompanyBoardService {

	private final AccompanyBoardRepository accompanyBoardRepository;
	private final AccompanyImageService accompanyImageService;
	private final AccompanyTagService accompanyTagService;
	private final AccompanyUserService accompanyUserService;
	private final AccompanyRequestService accompanyRequestService;

	@Transactional
	public CreateAccompanyBoardResponse create(Long userId, CreateAccompanyBoardRequest request) {
		AccompanyBoard accompanyBoard = accompanyBoardRepository.save(
			builder()
				.title(request.title())
				.content(request.content())
				.region(request.region())
				.startDate(request.startDate())
				.endDate(request.endDate())
				.headCount(1L)
				.capacity(request.capacity())
				.category(request.category())
				.preferredAge(request.preferredAge())
				.preferredGender(request.preferredGender())
				.build()
		);
		accompanyImageService.save(accompanyBoard, request.imageUrls());
		accompanyTagService.save(accompanyBoard, request.tagNames());
		accompanyUserService.save(userId, accompanyBoard, HOST);

		return new CreateAccompanyBoardResponse(accompanyBoard.getId());
	}

	@Transactional(readOnly = true)
	public PageResponse<AccompanyBoardThumbnail> readAll(Pageable pageable, Region region) {
		Slice<FindBoardThumbnailsResult> sliceResult = accompanyBoardRepository.findBoardThumbnails(pageable, region);

		List<AccompanyBoardThumbnail> thumbnails = getAccompanyBoardThumbnails(sliceResult.getContent());

		return new PageResponse<>(sliceResult.hasNext(), thumbnails);
	}

	/**
	 * imageUrls 타입을 String -> List<String>로 변환합니다.
	 */
	private static List<AccompanyBoardThumbnail> getAccompanyBoardThumbnails(List<FindBoardThumbnailsResult> results) {
		List<AccompanyBoardThumbnail> thumbnails = results.stream()
			.map(result -> AccompanyBoardThumbnail.builder()
				.boardId(result.boardId())
				.title(result.title())
				.region(result.region())
				.startDate(result.startDate())
				.endDate(result.endDate())
				.nickname(result.nickname())
				.imageUrls(result.getImageUrlsAsList())
				.build())
			.toList();
		return thumbnails;
	}

	@Transactional(readOnly = true)
	public ReadAccompanyBoardResponse read(Long boardId) {
		FindDetailInfoResult detailInfoResult = accompanyBoardRepository.findDetailInfo(boardId)
			.orElseThrow(() -> new AccompanyBoardNotFoundException(ErrorCode.ACCOMPANY_BOARD_NOT_FOUND));

		DetailInfo detailInfo = getDetailInfo(detailInfoResult);

		AccompanyBoardDetailInfo accompanyBoardDetailInfo = getAccompanyBoardDetailInfo(detailInfo);
		UserProfileDetailInfo userProfileDetailInfo = getUserProfileDetailInfo(detailInfo);

		return new ReadAccompanyBoardResponse(accompanyBoardDetailInfo, userProfileDetailInfo);
	}

	/**
	 * tagNames 타입을 String -> List<String>로 변환합니다.
	 * userImageUrls 타입을 String -> List<String>로 변환합니다.
	 */
	private static DetailInfo getDetailInfo(FindDetailInfoResult result) {
		return DetailInfo.builder()
			.boardId(result.boardId())
			.title(result.title())
			.content(result.content())
			.tagNames(result.getTagNamesAsList())
			.region(result.region())
			.startDate(result.startDate())
			.endDate(result.endDate())
			.headCount(result.headCount())
			.capacity(result.capacity())
			.category(result.category())
			.preferredAge(result.preferredAge())
			.preferredGender(result.preferredGender())
			.nickname(result.nickname())
			.provider(result.provider())
			.birthYear(result.birthYear())
			.gender(result.gender())
			.travelPreferences(result.travelPreferences())
			.travelStyles(result.travelStyles())
			.foodPreferences(result.foodPreferences())
			.userImageUrls(result.getUserImageUrlsAsList())
			.build();
	}

	@Transactional
	public void delete(Long userId, Long boardId) {
		if (accompanyUserService.isHostOfBoard(userId, boardId)) {
			accompanyImageService.deleteByBoardId(boardId);
			accompanyTagService.deleteByBoardId(boardId);
			accompanyRequestService.deleteByBoardId(boardId);
			accompanyUserService.deleteByBoardId(boardId);
			accompanyBoardRepository.deleteById(boardId);
		} else {
			throw new AccompanyBoardAccessDeniedException(ErrorCode.ACCOMPANY_BOARD_ACCESS_DENIED);
		}
	}

	private UserProfileDetailInfo getUserProfileDetailInfo(DetailInfo detailInfo) {
		return UserProfileDetailInfo.builder()
			.nickname(detailInfo.getNickname())
			.provider(detailInfo.getProvider())
			.birthYear(detailInfo.getBirthYear())
			.gender(detailInfo.getGender())
			.travelPreferences(detailInfo.getTravelPreferences())
			.travelStyles(detailInfo.getTravelStyles())
			.foodPreferences(detailInfo.getFoodPreferences())
			.userImageUrls(detailInfo.getUserImageUrls())
			.build();
	}

	private AccompanyBoardDetailInfo getAccompanyBoardDetailInfo(DetailInfo detailInfo) {
		return AccompanyBoardDetailInfo.builder()
			.boardId(detailInfo.getBoardId())
			.title(detailInfo.getTitle())
			.tagNames(detailInfo.getTagNames())
			.content(detailInfo.getContent())
			.tagNames(detailInfo.getTagNames())
			.region(detailInfo.getRegion())
			.startDate(detailInfo.getStartDate())
			.endDate(detailInfo.getEndDate())
			.headCount(detailInfo.getHeadCount())
			.capacity(detailInfo.getCapacity())
			.category(detailInfo.getCategory())
			.preferredAge(detailInfo.getPreferredAge())
			.preferredGender(detailInfo.getPreferredGender())
			.build();
	}
}
