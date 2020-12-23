package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.ReviewDTO;
import storysflower.com.storysflower.repositories.ReviewRepository;
import storysflower.com.storysflower.services.ReviewService;

import java.util.List;

/**
 * @author ntynguyen
 */
@Component
public class ReviewServiceImpl implements ReviewService {
    @Autowired
    ReviewRepository reviewRepository;

    @Override
    public List<ReviewDTO> getAllReviewsByProductId(Long productId) {
        return reviewRepository.getAllReviewsByProductId(productId);
    }

    @Override
    public boolean insertReview(Long productId, Long userId, String content) {
        return reviewRepository.insertReivew(productId, userId, content);
    }

    @Override
    public List<ReviewDTO> getAllNewReiview(int reviewListSize, Long productId) {
        return reviewRepository.getAllNewReview(reviewListSize, productId);
    }
}
