package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import static storysflower.com.storysflower.model.tables.tables.Favourite.FAVOURITE;

/**
 * @author ntynguyen
 */
@Repository
public class FavouriteRepository {
    @Autowired
    private DSLContext dslContext;

    public boolean getFavouriteByUserId(Long productId, Long userId) {
        Boolean isFavourite = dslContext.select(FAVOURITE.VALUE).from(FAVOURITE)
                .where(FAVOURITE.PRODUCT_ID.eq(productId))
                .and(FAVOURITE.USER_ID.eq(userId))
                .fetchOneInto(Boolean.class);
        return isFavourite == null ? false : isFavourite;
    }

    public boolean updateFavourite(Long productId, Long userId, Boolean isFavourite) {
        if (checkHaveFavourite(productId, userId)) {
            return insertFavourite(productId, userId, isFavourite);
        }
        return dslContext.update(FAVOURITE)
                .set(FAVOURITE.VALUE, isFavourite)
                .where(FAVOURITE.PRODUCT_ID.eq(productId))
                .and(FAVOURITE.USER_ID.eq(userId))
                .execute() > 0;
    }

    public boolean checkHaveFavourite(Long productId, Long userId) {
        return dslContext.select().from(FAVOURITE)
                .where(FAVOURITE.USER_ID.eq(userId))
                .and(FAVOURITE.PRODUCT_ID.eq(productId))
                .fetchCount() == 0;
    }

    public boolean insertFavourite(Long productId, Long userId, Boolean isFavourite) {
        return dslContext.insertInto(FAVOURITE)
                .set(FAVOURITE.PRODUCT_ID, productId)
                .set(FAVOURITE.USER_ID, userId)
                .set(FAVOURITE.VALUE, isFavourite)
                .execute() > 0;
    }
}
