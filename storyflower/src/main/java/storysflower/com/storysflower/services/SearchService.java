package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.ProductDTO;

import java.util.List;

public interface SearchService {
    List<ProductDTO> getListFlowerByKey(String searchKey);
}
