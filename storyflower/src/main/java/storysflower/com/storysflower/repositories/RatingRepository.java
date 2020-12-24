package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static storysflower.com.storysflower.model.tables.tables.Product.PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.Rating.RATING;

/**
 * @author ntynguyen
 */
@Repository
public class RatingRepository {
    @Autowired
    DSLContext dslContext;

    public int getRate(Long productId) {
        List<Integer> rates = dslContext
                .select(RATING.VALUE)
                .from(RATING)
                .join(PRODUCT).on(PRODUCT.ID.eq(RATING.PRODUCT_ID))
                .where(RATING.PRODUCT_ID.eq(productId))
                .fetchInto(Integer.class);
        return rates.size() == 0 ? 0 : (int) Math.round(rates.stream().mapToInt(t -> t).average().orElse(0));
    }

    public int getRateProductByUserId(Long productId, Long userId) {
        Integer rate = dslContext.select(RATING.VALUE)
                .from(RATING)
                .where(RATING.PRODUCT_ID.eq(productId))
                .and(RATING.USER_ID.eq(userId))
                .fetchOneInto(Integer.class);

        return rate == null ? 0 : rate;
    }

    public boolean updateRatingOfActivityByUserId(Long productId, Long userId, int value) {
        if (checkUserHaveRating(productId, userId)) {
            insertRatingOfAcivityByUserId(productId, userId, value);
        }
        return dslContext.update(RATING)
                .set(RATING.VALUE, value)
                .where(RATING.USER_ID.eq(userId))
                .and(RATING.PRODUCT_ID.eq(productId))
                .execute() > 0;
    }

    public boolean insertRatingOfAcivityByUserId(Long productId, Long userId, int value) {
        return dslContext.insertInto(RATING)
                .set(RATING.VALUE, value)
                .set(RATING.PRODUCT_ID, productId)
                .set(RATING.USER_ID, userId)
                .execute() > 0;
    }

    public boolean checkUserHaveRating(Long productId, Long userId) {
        return dslContext.selectFrom(RATING)
                .where(RATING.PRODUCT_ID.eq(productId))
                .and(RATING.USER_ID.eq(userId))
                .fetchCount() == 0;
    }
}
