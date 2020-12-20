package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.ProductDTO;
import storysflower.com.storysflower.repositories.DealRepository;
import storysflower.com.storysflower.services.DealService;

import java.util.List;

/**
 * @author ntynguyen
 */
@Component
public class DealProductServiceImpl implements DealService {
    @Autowired
    DealRepository dealRepository;

    @Override
    public List<ProductDTO> findAllDealProducts() {
        return dealRepository.findAllDealProducts();
    }
}
