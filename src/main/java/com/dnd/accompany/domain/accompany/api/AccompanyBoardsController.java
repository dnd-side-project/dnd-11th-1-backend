package com.dnd.accompany.domain.accompany.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardInfo;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardRequest;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardResponse;
import com.dnd.accompany.domain.accompany.api.dto.PageResponse;
import com.dnd.accompany.domain.accompany.service.AccompanyBoardsService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/accompany/boards")
@RequiredArgsConstructor
public class AccompanyBoardsController {

	private final AccompanyBoardsService accompanyBoardsService;

	@Operation(summary = "동행글 생성")
	@PostMapping
	public ResponseEntity<CreateAccompanyBoardResponse> create(
		@RequestBody @Valid CreateAccompanyBoardRequest request) {
		CreateAccompanyBoardResponse response = accompanyBoardsService.create(request);

		return ResponseEntity.ok(response);
	}

	@Operation(summary = "동행글 목록 조회")
	@GetMapping
	public ResponseEntity<PageResponse<AccompanyBoardInfo>> readAll(
		@RequestParam(value = "page", defaultValue = "0") int page,
		@RequestParam(value = "size", defaultValue = "10") int size) {
		return ResponseEntity.ok(accompanyBoardsService.readAll(page, size));
	}
}
