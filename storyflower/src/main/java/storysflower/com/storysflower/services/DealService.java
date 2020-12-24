package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.ProductDTO;

import java.util.List;

/**
 * @author ntynguyen
 */
public interface DealService {
    public List<ProductDTO> findAllDealProducts();
}
