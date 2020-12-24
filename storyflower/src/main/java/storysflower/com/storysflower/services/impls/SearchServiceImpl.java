package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.ProductDTO;
import storysflower.com.storysflower.repositories.SearchRepository;
import storysflower.com.storysflower.services.SearchService;

import java.util.List;

@Component
public class SearchServiceImpl implements SearchService {
    @Autowired
    private SearchRepository searchRepository;

    @Override
    public List<ProductDTO> getListFlowerByKey(String searchKey) {
        return searchRepository.getListFlowerByKey(searchKey);
    }
}
