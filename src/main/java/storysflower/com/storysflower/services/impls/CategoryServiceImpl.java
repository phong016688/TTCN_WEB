package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.repositories.CategoryRepository;
import storysflower.com.storysflower.services.CategoryService;

import java.util.List;

/**
 * @author ntynguyen
 */
@Component
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    public List<String> getCategories() {
        return categoryRepository.getCategories();
    }
}
