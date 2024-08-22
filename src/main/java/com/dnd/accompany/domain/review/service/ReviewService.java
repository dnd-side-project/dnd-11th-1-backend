package com.dnd.accompany.domain.review.service;

import com.dnd.accompany.domain.review.api.dto.CreateReviewRequest;
import com.dnd.accompany.domain.review.entity.Review;
import com.dnd.accompany.domain.review.infrastructure.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public Long create(CreateReviewRequest request) {
        Review review = Review.of(
                request.writerId(),
                request.accompanyBoardId(),
                request.satisfactionLevel(),
                request.recommendationStatus(),
                request.companionType(),
                request.personalityType(),
                request.travelPreference(),
                request.travelStyle(),
                request.detailContent(),
                request.reviewImageUrls()
        );

        reviewRepository.save(review);

        return review.getId();
    }
}
