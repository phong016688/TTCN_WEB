package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.RecipientCartDTO;

import static storysflower.com.storysflower.model.tables.Tables.*;

@Repository
public class RecipientRepository {
    @Autowired
    DSLContext dslContext;

    public RecipientCartDTO getRecipientCartDTObyIdBuyProduct(Long idProduct) {
        return dslContext.select(RECIPIENT.ID, RECIPIENT.FULL_NAME.as("fullName"), RECIPIENT.ADDRESS, RECIPIENT.PHONE_NUMBER.as("phoneNumber"), CART.DELIVERY_DATE.as("deliveryDate"), CART.DELIVERY_HOUR.as("deliveryHour"))
                .from(BUY_PRODUCT)
                .join(CART).on(BUY_PRODUCT.CART_ID.eq(CART.ID))
                .join(RECIPIENT).on(CART.RECIPIENT_ID.eq(RECIPIENT.ID))
                .where(BUY_PRODUCT.ID.eq(idProduct))
                .fetchOneInto(RecipientCartDTO.class);
    }
}
