package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.repositories.FavouriteRepository;
import storysflower.com.storysflower.services.FavouriteService;

/**
 * @author ntynguyen
 */
@Component
public class FavouriteServiceImpl implements FavouriteService {
    @Autowired
    FavouriteRepository favouriteRepository;

    @Override
    public boolean getFavouriteByUserId(Long productId, Long userId) {
        return favouriteRepository.getFavouriteByUserId(productId, userId);
    }

    @Override
    public boolean updateFavourite(Long productId, Long userId, boolean isFavourite) {
        return favouriteRepository.updateFavourite(productId, userId, isFavourite);
    }
}
