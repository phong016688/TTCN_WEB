package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.CustomerCartDTO;
import storysflower.com.storysflower.dto.CustomerDTO;
import storysflower.com.storysflower.dto.ProductCustomerDTO;
import storysflower.com.storysflower.repositories.CustomerRepository;
import storysflower.com.storysflower.services.CustomerService;

import java.util.List;

@Component
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public int countPagination() {
        return customerRepository.countPagination();
    }

    @Override
    public List<CustomerDTO> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerDTO findCustomerById(Long id) {
        return customerRepository.findCustomerById(id);
    }

    @Override
    public List<ProductCustomerDTO> findAllProductByIdCustomer(Long id) {
        List<ProductCustomerDTO> listProductByIdCustomer = customerRepository.findAllProductByIdCustomer(id);
        System.out.println("s: " + listProductByIdCustomer.size());
        if (listProductByIdCustomer.size() > 0) {
            int i = 1;
            for (ProductCustomerDTO product : listProductByIdCustomer) {
                product.setId(i++);
                product.setTotal_Money(product.getPrice() * product.getQuantity());
            }
        }
        return listProductByIdCustomer;
    }

    @Override
    public CustomerCartDTO findCustomerCartDTOByIdBuyProduct(Long idProduct) {
        return customerRepository.getCustomerCartDTOByIdBuyProduct(idProduct);
    }
}
