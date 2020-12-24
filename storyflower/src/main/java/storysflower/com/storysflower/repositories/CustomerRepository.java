package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.CustomerCartDTO;
import storysflower.com.storysflower.dto.CustomerDTO;
import storysflower.com.storysflower.dto.ProductCustomerDTO;

import java.util.Collections;
import java.util.List;

import static storysflower.com.storysflower.model.tables.Tables.*;
import static storysflower.com.storysflower.model.tables.tables.Product.PRODUCT;

@Repository
public class CustomerRepository {
    @Autowired
    DSLContext dslContext;

    public int countPagination() {
        return dslContext.selectCount()
                .from(CUSTOMER)
                .fetchOne(0, Integer.class);
    }

    public CustomerDTO findCustomerById(Long id) {
        CustomerDTO customerDTO = dslContext
                .select(CUSTOMER.ID, CUSTOMER.ADDRESS, CUSTOMER.EMAIL, CUSTOMER.FULL_NAME.as("fullName"), CUSTOMER.PHONE_NUMBER.as("phoneNumber"))
                .from(CUSTOMER)
                .where(CUSTOMER.ID.eq(id))
                .fetchOneInto(CustomerDTO.class);
        return customerDTO;
    }

    public List<CustomerDTO> findAll() {
        List<CustomerDTO> listCustomerProfile = dslContext
                .select(CUSTOMER.ID, CUSTOMER.ADDRESS, CUSTOMER.EMAIL, CUSTOMER.FULL_NAME.as("fullName"), CUSTOMER.PHONE_NUMBER.as("phoneNumber"))
                .from(CUSTOMER)
                .orderBy(CUSTOMER.ID)
                .fetchInto(CustomerDTO.class);
        return listCustomerProfile.size() == 0 ? Collections.emptyList() : listCustomerProfile;
    }

    public List<ProductCustomerDTO> findAllProductByIdCustomer(Long id) {
        List<ProductCustomerDTO> listProductByCustomer = dslContext
                .select(PRODUCT.PRODUCT_NAME, PRODUCT.PRICE, BUY_PRODUCT.QUANTITY, CART.BUY_DATE)
                .from(CART)
                .join(BUY_PRODUCT)
                .on(CART.ID.eq(BUY_PRODUCT.CART_ID))
                .join(CUSTOMER)
                .on(CUSTOMER.ID.eq(CART.CUSTOMER_ID))
                .join(PRODUCT)
                .on(PRODUCT.ID.eq(BUY_PRODUCT.PRODUCT_ID))
                .where(CUSTOMER.ID.eq(id))
                .fetchInto(ProductCustomerDTO.class);
        return listProductByCustomer.size() == 0 ? Collections.emptyList() : listProductByCustomer;
    }

    //String fullName, Date buyDate, String address, String phoneNumber, String email, String messagesToUs
    public CustomerCartDTO getCustomerCartDTOByIdBuyProduct(Long idBuyProduct) {
        return dslContext.select(CUSTOMER.FULL_NAME.as("fullName"), CART.BUY_DATE.as("buyDate"), CUSTOMER.ADDRESS, CUSTOMER.PHONE_NUMBER.as("phoneNumber"), CUSTOMER.EMAIL, RECIPIENT.MESSAGE_TO_US.as("messagesToUs"))
                .from(BUY_PRODUCT)
                .join(CART).on(BUY_PRODUCT.CART_ID.eq(CART.ID))
                .join(CUSTOMER).on(CART.CUSTOMER_ID.eq(CUSTOMER.ID))
                .join(RECIPIENT).on(CART.RECIPIENT_ID.eq(RECIPIENT.ID))
                .where(BUY_PRODUCT.ID.eq(idBuyProduct))
                .fetchOneInto(CustomerCartDTO.class);
    }
}
