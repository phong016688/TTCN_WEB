package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import storysflower.com.storysflower.dto.*;
import storysflower.com.storysflower.model.tables.Tables;
import storysflower.com.storysflower.utils.DateUtil;

import java.sql.Date;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import static storysflower.com.storysflower.model.tables.Tables.*;
import static storysflower.com.storysflower.model.tables.Tables.IMAGE_PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.Cart.CART;
import static storysflower.com.storysflower.model.tables.tables.Customer.CUSTOMER;
import static storysflower.com.storysflower.model.tables.tables.Product.PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.Recipient.RECIPIENT;

@Repository
public class CartRepository {

    @Autowired
    private DSLContext dslContext;

    @Transactional
    public boolean updataCartData(ReceiptDTO receiptDTO, List<CartDTO> cartDTOList) {
        CustomerDTO customerDTO = receiptDTO.getCustomerDTO();
        RecipientDTO recipientDTO = receiptDTO.getRecipientDTO();
        insertCustomerData(customerDTO);
        insertRecipientData(recipientDTO);
        insertCartData(customerDTO, recipientDTO, DateUtil.convertFromStringToDaTe(receiptDTO.getDeliveryDate()),DateUtil.convertFromStringToTimeStamp(receiptDTO.getDeliveryHour()));
        insertBuyData(cartDTOList, getCartId(getCustomerId(customerDTO.getEmail()), getRecipientId(recipientDTO)));
        return true;
    }

    public int insertCartData(CustomerDTO customerDTO, RecipientDTO recipientDTO, Date deliveryDate, Time deliveryHour) {
        return (dslContext.insertInto(CART)
                .set(CART.CUSTOMER_ID, getCustomerId(customerDTO.getEmail()))
                .set(CART.RECIPIENT_ID, getRecipientId(recipientDTO))
                .set(CART.DELIVERY_DATE, deliveryDate)
                .set(CART.DELIVERY_HOUR, deliveryHour)
                .execute());
    }

    public int insertBuyData(List<CartDTO> cartDTOList, Long cartId) {
        for (CartDTO cartDTO : cartDTOList) {
            dslContext.insertInto(BUY_PRODUCT)
                    .set(BUY_PRODUCT.CART_ID, cartId)
                    .set(BUY_PRODUCT.PRODUCT_ID, cartDTO.getProductDTO().getId())
                    .set(BUY_PRODUCT.QUANTITY, cartDTO.getQuantity())
                    .execute();
        }
        return 1;
    }


    @Transactional
    public int insertCustomerData(CustomerDTO customerDTO) {
        return dslContext.insertInto(CUSTOMER)
                .set(CUSTOMER.FULL_NAME, customerDTO.getFullName())
                .set(CUSTOMER.ADDRESS, customerDTO.getAddress())
                .set(CUSTOMER.EMAIL, customerDTO.getEmail())
                .set(CUSTOMER.PHONE_NUMBER, customerDTO.getPhoneNumber())
                .execute();
    }

    @Transactional
    public int insertRecipientData(RecipientDTO recipientDTO) {
        return dslContext.insertInto(RECIPIENT)
                .set(RECIPIENT.FULL_NAME, recipientDTO.getFullName())
                .set(RECIPIENT.ADDRESS, recipientDTO.getAddress())
                .set(RECIPIENT.PHONE_NUMBER, recipientDTO.getPhoneNumber())
                .set(RECIPIENT.MESSAGE_TO_RECIPIENT, recipientDTO.getMessageToRecipient())
                .set(RECIPIENT.MESSAGE_TO_US, recipientDTO.getMessageToUs())
                .execute();
    }

    public Long getCustomerId(String email) {
        return dslContext.select(CUSTOMER.ID)
                .from(CUSTOMER)
                .where(CUSTOMER.EMAIL.eq(email))
                .fetchOneInto(Long.class);
    }

    public Long getRecipientId(RecipientDTO recipientDTO) {
        return dslContext.select(RECIPIENT.ID)
                .from(RECIPIENT)
                .where(RECIPIENT.FULL_NAME.eq(recipientDTO.getFullName()))
                .and(RECIPIENT.ADDRESS.eq(recipientDTO.getAddress()))
                .and(RECIPIENT.PHONE_NUMBER.eq(recipientDTO.getPhoneNumber()))
                .fetchOneInto(Long.class);
    }

    public Long getCartId(Long customerId, Long recipientId) {
        return dslContext.select(CART.ID)
                .from(CART)
                .where(CART.CUSTOMER_ID.eq(customerId))
                .and(CART.RECIPIENT_ID.eq(recipientId))
                .fetchOneInto(Long.class);
    }

    public List<CartAdminDTO> findAll() {
        List<CartAdminDTO> listCart = dslContext
                .select(CART.ID, CUSTOMER.FULL_NAME, CART.DELIVERY_DATE, RECIPIENT.MESSAGE_TO_US, CART.STATUS)
                .from(CART)
                .join(RECIPIENT).on(Tables.CART.RECIPIENT_ID.eq(RECIPIENT.ID))
                .join(Tables.CUSTOMER).on(Tables.CUSTOMER.ID.eq(Tables.CART.CUSTOMER_ID))
                .fetchInto(CartAdminDTO.class);
        return listCart.size() == 0 ? Collections.emptyList() : listCart;
    }

    public List<ProductCartDTO> getAllListProductByIdCart(Long idCart) {
        List<ProductCartDTO> listProduct = dslContext
                //String productName,  String messageToRecipient, Double price, Integer quantity
                .select(IMAGE_PRODUCT.IMAGE_ID,PRODUCT.PRODUCT_NAME.as("productName"), RECIPIENT.MESSAGE_TO_RECIPIENT.as("messageToRecipient"), PRODUCT.PRICE, BUY_PRODUCT.QUANTITY)
                .from(PRODUCT)
                .join(BUY_PRODUCT).on(PRODUCT.ID.eq(BUY_PRODUCT.PRODUCT_ID))
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .join(CART).on(CART.ID.eq(BUY_PRODUCT.CART_ID))
                .join(RECIPIENT).on(CART.RECIPIENT_ID.eq(RECIPIENT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .where(BUY_PRODUCT.CART_ID.eq(idCart))
                .fetchInto(ProductCartDTO.class);
        return listProduct.size() == 0 ? Collections.emptyList() : listProduct;
    }

    public boolean updateStatus(Long id) {
        return dslContext.update(CART)
                .set(CART.STATUS, 1)
                .where(CART.ID.eq(id))
                .execute() > 0;
    }

    public int countPagination() {
        return dslContext.selectCount()
                .from(BUY_PRODUCT)
                .fetchOne(0, Integer.class);
    }


    public int getStatus(Long id) {
        return dslContext.select(CART.STATUS)
                .from(CART)
                .where(CART.ID.eq(id))
                .fetchOne(0, Integer.class);
    }
}
