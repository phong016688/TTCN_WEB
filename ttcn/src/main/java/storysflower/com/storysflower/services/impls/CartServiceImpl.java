package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.CartAdminDTO;
import storysflower.com.storysflower.dto.CartDTO;
import storysflower.com.storysflower.dto.ProductCartDTO;
import storysflower.com.storysflower.dto.ReceiptDTO;
import storysflower.com.storysflower.repositories.CartRepository;
import storysflower.com.storysflower.services.CartService;
import storysflower.com.storysflower.services.CustomerService;
import storysflower.com.storysflower.services.ProductService;

import java.util.List;

/**
 * @author ntynguyen
 */
@Component
public class CartServiceImpl implements CartService {
    @Autowired
    private ProductService productService;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerService customerService;


    @Override
    public boolean addCard(List<CartDTO> cartDTOList, Long productId, String quantity) {
        CartDTO cartDTO = new CartDTO(productService.getProductDTOById(productId), Integer.parseInt(quantity));
        for (CartDTO cartDTO1 : cartDTOList) {
            if (cartDTO.getProductDTO().getId().equals(cartDTO1.getProductDTO().getId())) {
                return false;
            }
        }
        return cartDTOList.add(cartDTO);
    }

    @Override
    public double calculateTotal(List<CartDTO> cartDTOList) {
        return cartDTOList.stream().mapToDouble(t -> (t.getQuantity() * t.getProductDTO().getPrice())).sum();
    }

    @Override
    public boolean updateCartData(ReceiptDTO receiptDTO, List<CartDTO> cartDTOList) {
        return cartRepository.updataCartData(receiptDTO, cartDTOList);
    }

    @Override
    public List<CartAdminDTO> findAll() {
        List<CartAdminDTO> list = cartRepository.findAll();
        return list;
    }

    @Override
    public int countPagination() {
        return cartRepository.countPagination();
    }

    @Override
    public List<ProductCartDTO> findAllListProductByIdCart(Long idCart) {
        List<ProductCartDTO> l = cartRepository.getAllListProductByIdCart(idCart);
        int i = 1;
        for (ProductCartDTO p : l) {
            p.setId(i);
            i++;
            p.setTotalMoney(p.getPrice() * p.getQuantity());
        }
        return l;
    }

    @Override
    public boolean updateStatus(Long id) {
        return cartRepository.updateStatus(id);
    }

    @Override
    public int getStatus(Long id) {
        return cartRepository.getStatus(id);
    }


}
