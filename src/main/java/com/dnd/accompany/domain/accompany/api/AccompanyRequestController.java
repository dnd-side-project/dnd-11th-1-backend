package com.dnd.accompany.domain.accompany.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.accompany.domain.accompany.api.dto.ReadAccompanyResponse;
import com.dnd.accompany.domain.accompany.service.AccompanyServiceFacade;
import com.dnd.accompany.domain.auth.dto.jwt.JwtAuthentication;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "AccompanyRequest")
@RestController
@RequestMapping("api/v1/accompany/requests")
@RequiredArgsConstructor
public class AccompanyRequestController {

	private final AccompanyServiceFacade accompanyServiceFacade;

	@Operation(summary = "동행 신청서 조회")
	@GetMapping("/{id}")
	public ResponseEntity<ReadAccompanyResponse> read(
		@PathVariable(name = "id") Long boardId,
		@AuthenticationPrincipal JwtAuthentication user,
		@RequestParam(value = "applicantId", required = false) Long applicantId) {
		return ResponseEntity.ok(accompanyServiceFacade.getRequestDetail(boardId, user.getId(), applicantId));
	}
}
