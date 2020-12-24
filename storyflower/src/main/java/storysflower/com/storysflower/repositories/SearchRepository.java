package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.ProductDTO;

import java.util.List;

import static storysflower.com.storysflower.model.tables.Tables.IMAGE_PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.Product.PRODUCT;

@Repository
public class SearchRepository {
    @Autowired
    private DSLContext dslContext;

    public List<ProductDTO> getListFlowerByKey(String searchKey) {
        return dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .where(PRODUCT.PRODUCT_NAME.like("%" + searchKey + "%"))
                .fetchInto(ProductDTO.class);
    }
}
