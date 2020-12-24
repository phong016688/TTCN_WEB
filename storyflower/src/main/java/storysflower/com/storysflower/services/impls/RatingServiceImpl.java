package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.ProductDTO;
import storysflower.com.storysflower.repositories.RatingRepository;
import storysflower.com.storysflower.services.RattingService;

import java.util.List;

/**
 * @author ntynguyen
 */
@Component
public class RatingServiceImpl implements RattingService {
    @Autowired
    RatingRepository ratingRepository;

    @Override
    public int getRate(Long productId) {
        return ratingRepository.getRate(productId);
    }

    @Override
    public void setRating(List<ProductDTO> productDTOS) {
        for (ProductDTO productDTO : productDTOS) {
            productDTO.setRating(ratingRepository.getRate(productDTO.getId()));
        }
    }

    @Override
    public int getRateProductByUserId(Long productId, Long userId) {
        return ratingRepository.getRateProductByUserId(productId, userId);
    }

    @Override
    public boolean updateRatingOfActivityByUserId(Long productId, Long userId, int value) {
        return ratingRepository.updateRatingOfActivityByUserId(productId, userId, value);
    }
}
