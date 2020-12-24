package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.ImageDTO;
import storysflower.com.storysflower.model.tables.tables.records.ImageRecord;

import static storysflower.com.storysflower.model.tables.Tables.IMAGE_PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.Image.IMAGE;

/**
 * @author ntynguyen
 */
@Component
public class ImageRepository {
    @Autowired
    private DSLContext dslContext;

    public ImageDTO findImageById(long imageId) {
        ImageRecord image = dslContext
                .selectFrom(IMAGE)
                .where(IMAGE.IMAGE_ID.eq(imageId))
                .fetchOne();

        if (image == null) {
            return null;
        }
        return image.into(ImageDTO.class);
    }

    public Long addProductImage(long productId, Long imageId, byte[] imageData) {
        long id = dslContext.insertInto(IMAGE)
                .set(IMAGE.IMAGE_DATA, imageData)
                .returning(IMAGE.IMAGE_ID)
                .fetchOne().getImageId();
        if (imageId != 0) {
            dslContext.update(IMAGE_PRODUCT)
                    .set(IMAGE_PRODUCT.IMAGE_ID, id)
                    .where(IMAGE_PRODUCT.PRODUCT_ID.eq(productId))
                    .and(IMAGE_PRODUCT.IMAGE_ID.eq(imageId)).execute();
        } else {
            if (!checkIsFirstUpload(productId)) {
                dslContext.insertInto(IMAGE_PRODUCT)
                        .set(IMAGE_PRODUCT.IMAGE_ID, id)
                        .set(IMAGE_PRODUCT.PRODUCT_ID, productId)
                        .set(IMAGE_PRODUCT.MAIN_IMAGE, true).execute();
            }else{
                dslContext.insertInto(IMAGE_PRODUCT)
                        .set(IMAGE_PRODUCT.IMAGE_ID, id)
                        .set(IMAGE_PRODUCT.PRODUCT_ID, productId)
                        .set(IMAGE_PRODUCT.MAIN_IMAGE, false)
                        .execute();
            }

        }
        return id;
    }

    public boolean checkIsFirstUpload(Long productId) {
        return dslContext.select(IMAGE_PRODUCT.PRODUCT_ID)
                .from(IMAGE_PRODUCT)
                .where(IMAGE_PRODUCT.PRODUCT_ID.eq(productId))
                .fetchCount() > 0;
    }

    public boolean delImg(Long id) {
        return dslContext.delete(IMAGE_PRODUCT)
                .where(IMAGE_PRODUCT.PRODUCT_ID.eq(id))
                .execute() > 0;
    }
}
