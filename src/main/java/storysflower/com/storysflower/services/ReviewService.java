package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.ReviewDTO;

import java.util.List;

/**
 * @author ntynguyen
 */
public interface ReviewService {
    List<ReviewDTO> getAllReviewsByProductId(Long id);

    boolean insertReview(Long productId, Long id, String content);

    List<ReviewDTO> getAllNewReiview(int reviewListSize, Long productId);
}
