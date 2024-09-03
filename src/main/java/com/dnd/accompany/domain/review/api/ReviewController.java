package com.dnd.accompany.domain.review.api;

import com.dnd.accompany.domain.auth.dto.jwt.JwtAuthentication;
import com.dnd.accompany.domain.review.api.dto.AllEvaluationResponses;
import com.dnd.accompany.domain.review.api.dto.CreateReviewRequest;
import com.dnd.accompany.domain.review.api.dto.ReviewDetailsResponse;
import com.dnd.accompany.domain.review.api.dto.ReviewDetailsResult;
import com.dnd.accompany.domain.review.api.dto.SimpleEvaluationResponse;
import com.dnd.accompany.domain.review.api.dto.SimpleReviewResponses;
import com.dnd.accompany.domain.review.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Review")
@RestController
@RequestMapping("/api/v1/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @Operation(summary = "리뷰 생성")
    @PostMapping
    public ResponseEntity<Long> create(
            @AuthenticationPrincipal JwtAuthentication user,
            @RequestBody @Valid CreateReviewRequest request
    ) {
        Long id = reviewService.create(user.getId(), request);

        return ResponseEntity.ok(id);
    }

    @Operation(summary = "리뷰 상세 조회")
    @GetMapping("/{id}")
    public ResponseEntity<ReviewDetailsResponse> getDetails(
            @AuthenticationPrincipal JwtAuthentication user,
            @PathVariable("id") Long reviewId
    ) {
        ReviewDetailsResult result = reviewService.getReviewDetails(user.getId(), reviewId);
        ReviewDetailsResponse response = ReviewDetailsResponse.of(result);

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "받은 동행 후기 조회")
    @GetMapping
    public ResponseEntity<SimpleReviewResponses> getReviewList(
            @AuthenticationPrincipal JwtAuthentication user
    ) {
        SimpleReviewResponses reviewList = reviewService.getReviewList(user.getId());
        return ResponseEntity.ok(reviewList);
    }

    @Operation(summary = "받은 동행 평가 조회")
    @GetMapping("/evaluations")
    public ResponseEntity<SimpleEvaluationResponse> getEvaluation(
            @AuthenticationPrincipal JwtAuthentication user
    ) {
        SimpleEvaluationResponse response = reviewService.getSimpleEvaluation(user.getId());
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "받은 모든 동행 평가 조회")
    @GetMapping("/evaluations/all")
    public ResponseEntity<AllEvaluationResponses> getEvaluations(
            @AuthenticationPrincipal JwtAuthentication user
    ) {
        AllEvaluationResponses response = reviewService.getEvaluation(user.getId());
        return ResponseEntity.ok(response);
    }
}
