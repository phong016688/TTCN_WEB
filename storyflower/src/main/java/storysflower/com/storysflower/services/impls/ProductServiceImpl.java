package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.ProductCartDTO;
import storysflower.com.storysflower.dto.ProductDTO;
import storysflower.com.storysflower.dto.ProductDetailDTO;
import storysflower.com.storysflower.repositories.ImageRepository;
import storysflower.com.storysflower.repositories.ProductRepository;
import storysflower.com.storysflower.services.ProductService;

import java.util.List;

/**
 * @author ntynguyen
 */
@Component
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageRepository imageRepository;

    @Override
    public ProductDetailDTO getProductDetailDTOById(Long id) {
        return productRepository.getProductDetailByProductId(id);
    }

    @Override
    public ProductDTO getProductDTOById(Long productId) {
        return productRepository.getProductDTOById(productId);
    }

    @Override
    public List<ProductDTO> getListProductDTOByOccasionId(Long occasionId) {
        return productRepository.getListProductDTOByOccasionId(occasionId);
    }

    @Override
    public List<ProductDTO> getListBestProductDTOByRatting() {
        return productRepository.getListBestProductDTOByRatting();
    }

    @Override
    public List<ProductDTO> findListProductByIdCustomer(Long id) {
        return null;
    }

    @Override
    public List<ProductDTO> getListBestProductDTOBySellerAndOccasion(Long occasionId) {
        return productRepository.getListBestProductDTOBySellerAndOccasion(occasionId);
    }

    @Override
    public List<ProductDTO> getAllFlower() {
        return productRepository.getAllFlowers();
    }

    @Override
    public ProductCartDTO findProductCartByIdBuyProduct(Long idProduct) {
        ProductCartDTO productCartDTO = productRepository.getProductCartByIdBuyProduct(idProduct);
        productCartDTO.setTotalMoney(productCartDTO.getPrice() * productCartDTO.getQuantity());
        return productCartDTO;
    }

    @Override
    public List<ProductDTO> getAllPlants() {
        return productRepository.getAllPlant();
    }

    @Override
    public boolean editProduct(ProductDetailDTO productDetailDTO) {
        return productRepository.editProduct(productDetailDTO);
    }

    @Override
    public boolean addProduct(ProductDetailDTO productDetailDTO) {
        Long idProductLast = productRepository.addProduct(productDetailDTO);
        if(idProductLast>0) return  true;
        return  false;
    }

    @Override
    public boolean delProduct(Long id) {
        if(productRepository.delProductByID(id))
            return imageRepository.delImg(id);
        else return false;
    }

    @Override
    public List<ProductDTO> getAllGitfAndSweet() {
        return productRepository.getAllGitfSweet();
    }


}
