package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import storysflower.com.storysflower.dto.CategoryDTO;
import storysflower.com.storysflower.dto.ProductCartDTO;
import storysflower.com.storysflower.dto.ProductDTO;
import storysflower.com.storysflower.dto.ProductDetailDTO;
import storysflower.com.storysflower.services.RattingService;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static storysflower.com.storysflower.model.tables.Tables.*;
import static storysflower.com.storysflower.model.tables.tables.Product.PRODUCT;
import static storysflower.com.storysflower.model.tables.tables.Topic.TOPIC;

/**
 * @author ntynguyen
 */
@Repository
public class ProductRepository {
    @Autowired
    DSLContext dslContext;

    @Autowired
    RatingRepository ratingRepository;

    @Autowired
    RattingService rattingService;


    public ProductDetailDTO getProductDetailByProductId(Long id) {
        ProductDetailDTO productDetailDTO = dslContext
                .select(PRODUCT.ID, IMAGE_PRODUCT.IMAGE_ID, PRODUCT.PRODUCT_NAME, OCCASION.NAME_OCCASION, OCCASION.OCCASION_ID.as("idOccasion"), TOPIC.TOPIC_ID.as("idTopic"), TOPIC.TOPIC_NAME, PRODUCT.DESCRIPTION, PRODUCT.MEANING, PRODUCT.PRICE)
                .from(PRODUCT)
                .leftJoin(OCCASION).on(OCCASION.OCCASION_ID.eq(PRODUCT.OCCASION_ID))
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .join(TOPIC).on(TOPIC.TOPIC_ID.eq(PRODUCT.TOPIC_ID))
                .where(PRODUCT.ID.eq(id))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .fetchOneInto(ProductDetailDTO.class);
        List<CategoryDTO> categories = getCategoriesByProductId(id);
        if (categories.size() == 0) {
            productDetailDTO.setCategories(Collections.emptyList());
        } else {
            productDetailDTO.setCategories(categories);
        }
        productDetailDTO.setRating(ratingRepository.getRate(productDetailDTO.getId()));
        List<Long> imageIds = getListImageIdByProductId(id);
        if (imageIds.size() == 0) {
            productDetailDTO.setImageIds(Collections.emptyList());
        } else {
            productDetailDTO.setImageIds(imageIds);
        }
        return productDetailDTO;
    }

    public List<Long> getListImageIdByProductId(Long productId) {
        List<Long> imageIds = dslContext.select(IMAGE_PRODUCT.IMAGE_ID)
                .from(IMAGE_PRODUCT)
                .where(IMAGE_PRODUCT.PRODUCT_ID.eq(productId))
                .fetchInto(Long.class);
        return imageIds.size() == 0 ? Collections.emptyList() : imageIds;
    }

    public List<CategoryDTO> getCategoriesByProductId(Long productId) {
        List<CategoryDTO> categories = dslContext
                .select(CATEGORY.CATEGORY_NAME, CATEGORY.CATEGORY_MEANING)
                .from(PRODUCT_CATEGORY)
                .join(PRODUCT)
                .on(PRODUCT.ID.eq(PRODUCT_CATEGORY.PRODUCT_ID))
                .join(CATEGORY)
                .on(CATEGORY.ID.eq(PRODUCT_CATEGORY.CATEGORY_ID))
                .where(PRODUCT.ID.eq(productId))
                .fetchInto(CategoryDTO.class);
        return categories.size() == 0 ? Collections.emptyList() : categories;
    }

    public List<ProductDTO> getListProductDTOByOccasionId(Long occasionId) {
        List<ProductDTO> productDTOS = dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .where(PRODUCT.OCCASION_ID.eq(occasionId))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .fetchInto(ProductDTO.class);
        rattingService.setRating(productDTOS);
        return productDTOS.size() == 0 ? Collections.emptyList() : productDTOS;
    }

   /* public ProductDTO getLastProductDTO() {
        return dslContext.select()
                .from(PRODUCT)
                .
                .fetchInto(ProductDTO.class);


    }*/

    public List<ProductDTO> getListBestProductDTOByRatting() {
        List<ProductDTO> productDTOS = dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .fetchInto(ProductDTO.class);
        rattingService.setRating(productDTOS);
        return productDTOS.stream().sorted((t1, t2) -> t2.getRating() - t1.getRating()).collect(Collectors.toList()).subList(0, 3);
    }

    public ProductDTO getProductDTOById(Long productId) {
        return dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .where(PRODUCT.ID.eq(productId))
                .fetchAnyInto(ProductDTO.class);

    }

    public List<ProductDTO> getListBestProductDTOByRattingAndOccasion(Long occasionId) {
        List<ProductDTO> productDTOS = dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .where(PRODUCT.OCCASION_ID.eq(occasionId))
                .fetchInto(ProductDTO.class);
        rattingService.setRating(productDTOS);
        return productDTOS.stream().sorted((t1, t2) -> t2.getRating() - t1.getRating()).collect(Collectors.toList()).subList(0, 3);
    }

    public ProductDTO getProductByIdPro(Long id) {
        return dslContext.select()
                .from(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .fetchOneInto(ProductDTO.class);
    }


    public List<ProductDTO> getListBestProductDTOBySellerAndOccasion(Long occasionId) {
        return null;
    }

    public List<ProductDTO> getAllFlowers() {
        return dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .where(PRODUCT.TOPIC_ID.eq(1L))
                .fetchInto(ProductDTO.class);
    }

    public ProductCartDTO getProductCartByIdBuyProduct(Long idByProduct) {
        return dslContext.select(PRODUCT.PRODUCT_NAME.as("productName"), RECIPIENT.MESSAGE_TO_RECIPIENT.as("messageToRecipient"), PRODUCT.PRICE, BUY_PRODUCT.QUANTITY)
                .from(BUY_PRODUCT)
                .join(CART).on(BUY_PRODUCT.CART_ID.eq(CART.ID))
                .join(PRODUCT).on(BUY_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .join(RECIPIENT).on(CART.RECIPIENT_ID.eq(RECIPIENT.ID))
                .where(BUY_PRODUCT.ID.eq(idByProduct))
                .fetchOneInto(ProductCartDTO.class);
    }

    public List<ProductDTO> getAllGitfSweet() {
        return dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .where(PRODUCT.TOPIC_ID.eq(3L))
                .fetchInto(ProductDTO.class);
    }

    public List<ProductDTO> getAllPlant() {
        return dslContext.select()
                .from(PRODUCT)
                .join(IMAGE_PRODUCT).on(IMAGE_PRODUCT.PRODUCT_ID.eq(PRODUCT.ID))
                .and(IMAGE_PRODUCT.MAIN_IMAGE.eq(true))
                .where(PRODUCT.TOPIC_ID.eq(2L))
                .fetchInto(ProductDTO.class);
    }

    public boolean editProduct(ProductDetailDTO productDetailDTO) {
        return dslContext.update(PRODUCT)
                .set(PRODUCT.PRODUCT_NAME, productDetailDTO.getProductName())
                .set(PRODUCT.PRICE, productDetailDTO.getPrice())
                .set(PRODUCT.TOPIC_ID, productDetailDTO.getIdTopic())
                .set(PRODUCT.OCCASION_ID, productDetailDTO.getIdOccasion())
                .where(PRODUCT.ID.eq(productDetailDTO.getId()))
                .execute() > 0;
    }

    public Long addProduct(ProductDetailDTO productDetailDTO) {
        Long id = dslContext.insertInto(PRODUCT)
                .set(PRODUCT.TOPIC_ID, productDetailDTO.getIdTopic())
                .set(PRODUCT.OCCASION_ID, productDetailDTO.getIdOccasion())
                .set(PRODUCT.PRICE, productDetailDTO.getPrice())
                .set(PRODUCT.PRODUCT_NAME, productDetailDTO.getProductName())
                .set(PRODUCT.DESCRIPTION, productDetailDTO.getDescription())
                .returning(PRODUCT.ID)
                .fetchOne().getId();
        dslContext.insertInto(IMAGE_PRODUCT)
                .set(IMAGE_PRODUCT.PRODUCT_ID, id)
                .set(IMAGE_PRODUCT.IMAGE_ID, 238l)
                .set(IMAGE_PRODUCT.MAIN_IMAGE, true)
                .execute();
        return id;
    }
    public boolean delProductByID(Long id){
        return dslContext.delete(PRODUCT)
                .where(PRODUCT.ID.eq(id))
                .execute() > 0;
    }
}
