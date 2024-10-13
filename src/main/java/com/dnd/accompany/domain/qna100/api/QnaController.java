package com.dnd.accompany.domain.qna100.api;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnd.accompany.domain.auth.dto.jwt.JwtAuthentication;
import com.dnd.accompany.domain.qna100.api.dto.CreateAndUpdateQnaRequest;
import com.dnd.accompany.domain.qna100.api.dto.DeleteQnaRequest;
import com.dnd.accompany.domain.qna100.service.QnaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Tag(name = "QnA 100")
@RequiredArgsConstructor
@RequestMapping("api/v1/qna100s")
@RestController
public class QnaController {

	private final QnaService qnaService;

	@Operation(summary = "백문백답 추가 및 수정")
	@PostMapping("create-update")
	public ResponseEntity<Void> createAndUpdate(
		@RequestBody @Valid CreateAndUpdateQnaRequest request,
		@AuthenticationPrincipal JwtAuthentication user) {
		qnaService.saveAndUpdate(user.getId(), request);
		return ResponseEntity.ok().build();
	}

	@Operation(summary = "백문백답 삭제")
	@DeleteMapping
	public ResponseEntity<Void> delete(
		@RequestBody @Valid DeleteQnaRequest request,
		@AuthenticationPrincipal JwtAuthentication user) {
		qnaService.delete(user.getId(), request);
		return ResponseEntity.ok().build();
	}
}
