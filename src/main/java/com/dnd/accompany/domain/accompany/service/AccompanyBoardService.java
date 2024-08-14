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
import com.dnd.accompany.domain.accompany.api.dto.UserProfileThumbnail;
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
				.categories(request.categories())
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
	 * imageUrls의 타입을 String -> List<String>로 변환합니다.
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
		UserProfileThumbnail userProfileThumbnail = getUserProfileThumbnail(detailInfo);

		return new ReadAccompanyBoardResponse(accompanyBoardDetailInfo, userProfileThumbnail);
	}

	/**
	 * tagNames의 타입을 String -> List<String>로 변환합니다.
	 * imageUrls의 타입을 String -> List<String>로 변환합니다.
	 */
	private static DetailInfo getDetailInfo(FindDetailInfoResult result) {
		return DetailInfo.builder()
			.boardId(result.boardId())
			.title(result.title())
			.content(result.content())
			.tagNames(result.getTagNamesAsList())
			.imageUrls(result.getImageUrlsAsList())
			.region(result.region())
			.startDate(result.startDate())
			.endDate(result.endDate())
			.headCount(result.headCount())
			.capacity(result.capacity())
			.categories(result.getCategoriesAsList())
			.preferredAge(result.preferredAge())
			.preferredGender(result.preferredGender())
			.userId(result.userId())
			.nickname(result.nickname())
			.profileImageUrl(result.profileImageUrl())
			.birthYear(result.birthYear())
			.gender(result.gender())
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

	private UserProfileThumbnail getUserProfileThumbnail(DetailInfo detailInfo) {
		return UserProfileThumbnail.builder()
			.userId(detailInfo.getUserId())
			.nickname(detailInfo.getNickname())
			.profileImageUrl(detailInfo.getProfileImageUrl())
			.birthYear(detailInfo.getBirthYear())
			.gender(detailInfo.getGender())
			.build();
	}

	private AccompanyBoardDetailInfo getAccompanyBoardDetailInfo(DetailInfo detailInfo) {
		return AccompanyBoardDetailInfo.builder()
			.boardId(detailInfo.getBoardId())
			.title(detailInfo.getTitle())
			.tagNames(detailInfo.getTagNames())
			.content(detailInfo.getContent())
			.tagNames(detailInfo.getTagNames())
			.imageUrls(detailInfo.getImageUrls())
			.region(detailInfo.getRegion())
			.startDate(detailInfo.getStartDate())
			.endDate(detailInfo.getEndDate())
			.headCount(detailInfo.getHeadCount())
			.capacity(detailInfo.getCapacity())
			.categories(detailInfo.getCategories())
			.preferredAge(detailInfo.getPreferredAge())
			.preferredGender(detailInfo.getPreferredGender())
			.build();
	}
}
