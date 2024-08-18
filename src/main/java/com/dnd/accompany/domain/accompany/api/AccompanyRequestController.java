package com.dnd.accompany.domain.accompany.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyRequest;
import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyResponse;
import com.dnd.accompany.domain.accompany.service.AccompanyRequestService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "AccompanyBoard")
@RestController
@RequestMapping("api/v1/accompany/requests")
@RequiredArgsConstructor
public class AccompanyRequestController {

	private final AccompanyRequestService accompanyRequestService;

	@Operation(summary = "동행 신청서 조회")
	@PostMapping
	public ResponseEntity<ReadAccompanyResponse> read(ReadAccompanyRequest request) {
		return ResponseEntity.ok(accompanyRequestService.read(request));
	}
}
