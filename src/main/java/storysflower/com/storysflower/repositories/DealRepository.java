package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.ProductDTO;
import storysflower.com.storysflower.services.RattingService;

import java.util.List;

import static storysflower.com.storysflower.model.tables.Tables.DEAL_PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.ImageProduct.IMAGE_PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.Product.PRODUCT;

/**
 * @author ntynguyen
 */
@Repository
public class DealRepository {
    @Autowired
    private DSLContext dslContext;

    @Autowired
    private RattingService ratingService;

    public List<ProductDTO> findAllDealProducts() {
        List<ProductDTO> productDTOS = dslContext
                .select(PRODUCT.ID, IMAGE_PRODUCT.IMAGE_ID, PRODUCT.PRODUCT_NAME, PRODUCT.PRICE)
                .from(DEAL_PRODUCT)
                .innerJoin(PRODUCT).on(DEAL_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .innerJoin(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .where(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .fetchInto(ProductDTO.class);
        ratingService.setRating(productDTOS);
        return productDTOS;
    }

}
