package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.ReviewDTO;

import java.util.Collections;
import java.util.List;

import static storysflower.com.storysflower.model.tables.tables.Review.REVIEW;
import static storysflower.com.storysflower.model.tables.tables.User.USER;

/**
 * @author ntynguyen
 */
@Repository
public class ReviewRepository {
    @Autowired
    DSLContext dslContext;

    public List<ReviewDTO> getAllReviewsByProductId(Long productId) {
        List<ReviewDTO> reviewDTOS = dslContext
                .select(REVIEW.PRODUCT_ID, REVIEW.USER_ID, USER.EMAIL, USER.FIRSTNAME, USER.LASTNAME, USER.IMAGE_ID, REVIEW.CONTENT, REVIEW.DATE_CREATE)
                .from(REVIEW)
                .join(USER).on(USER.ID.eq(REVIEW.USER_ID))
                .where(REVIEW.PRODUCT_ID.eq(productId))
                .orderBy(REVIEW.DATE_CREATE.desc())
                .fetchInto(ReviewDTO.class);
        return reviewDTOS.size() == 0 ? Collections.emptyList() : reviewDTOS;
    }

    public boolean insertReivew(Long productId, Long userId, String content) {
        return dslContext.insertInto(REVIEW)
                .set(REVIEW.PRODUCT_ID, productId)
                .set(REVIEW.CONTENT, content)
                .set(REVIEW.USER_ID, userId)
                .execute() > 0;
    }

    public List<ReviewDTO> getAllNewReview(int reviewListSize, Long productId) {
        List<ReviewDTO> reviewDTOS = getAllReviewsByProductId(productId);
        return reviewDTOS.subList(0, reviewDTOS.size() - reviewListSize);
    }
}
