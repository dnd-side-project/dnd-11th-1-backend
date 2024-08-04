package com.dnd.accompany.domain.accompany.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.accompany.domain.accompany.api.dto.AccompanyBoardInfo;
import com.dnd.accompany.domain.accompany.api.dto.AccompanyRequestRequest;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardRequest;
import com.dnd.accompany.domain.accompany.api.dto.CreateAccompanyBoardResponse;
import com.dnd.accompany.domain.accompany.api.dto.PageResponse;
import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyBoardResponse;
import com.dnd.accompany.domain.accompany.service.AccompanyBoardService;
import com.dnd.accompany.domain.accompany.service.AccompanyRequestService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/accompany/boards")
@RequiredArgsConstructor
public class AccompanyBoardController {

	private final AccompanyBoardService accompanyBoardService;
	private final AccompanyRequestService accompanyRequestService;

	@Operation(summary = "동행글 생성")
	@PostMapping
	public ResponseEntity<CreateAccompanyBoardResponse> create(
		@RequestBody @Valid CreateAccompanyBoardRequest request) {
		return ResponseEntity.ok(accompanyBoardService.create(request));
	}

	@Operation(summary = "동행글 목록 조회")
	@GetMapping
	public ResponseEntity<PageResponse<AccompanyBoardInfo>> readAll(
		@RequestParam(value = "page", defaultValue = "0") int page,
		@RequestParam(value = "size", defaultValue = "10") int size) {
		return ResponseEntity.ok(accompanyBoardService.readAll(page, size));
	}

	@Operation(summary = "동행글 상세 조회")
	@GetMapping("/{id}")
	public ResponseEntity<ReadAccompanyBoardResponse> read(@PathVariable Long id) {
		return ResponseEntity.ok(accompanyBoardService.read(id));
	}

	@Operation(summary = "동행 신청")
	@PostMapping("/request")
	public ResponseEntity<Void> request(@RequestBody @Valid AccompanyRequestRequest request) {
		accompanyRequestService.save(request);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "동행글 삭제")
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		accompanyBoardService.delete(id);
		return ResponseEntity.ok().build();
	}
}
