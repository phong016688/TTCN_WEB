package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

import static storysflower.com.storysflower.model.tables.tables.Category.CATEGORY;

/**
 * @author ntynguyen
 */
@Repository
public class CategoryRepository {
    @Autowired
    DSLContext dslContext;

    public List<String> getCategories() {
        return dslContext
                .select(CATEGORY.CATEGORY_NAME)
                .from(CATEGORY)
                .fetchInto(String.class);
    }
}
