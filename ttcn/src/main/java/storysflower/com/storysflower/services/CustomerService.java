package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.CustomerCartDTO;
import storysflower.com.storysflower.dto.CustomerDTO;
import storysflower.com.storysflower.dto.ProductCustomerDTO;

import java.util.List;

public interface CustomerService {
    int countPagination();

    List<CustomerDTO> findAll();

    CustomerDTO findCustomerById(Long id);

    List<ProductCustomerDTO> findAllProductByIdCustomer(Long id);

    CustomerCartDTO findCustomerCartDTOByIdBuyProduct(Long idProduct);

}
