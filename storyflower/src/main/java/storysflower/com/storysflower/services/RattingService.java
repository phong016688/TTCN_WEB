package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.ProductDTO;

import java.util.List;

/**
 * @author ntynguyen
 */
public interface RattingService {
    int getRate(Long productId);

    void setRating(List<ProductDTO> productDTOS);

    int getRateProductByUserId(Long productId, Long userId);

    boolean updateRatingOfActivityByUserId(Long productId, Long userId, int value);
}
