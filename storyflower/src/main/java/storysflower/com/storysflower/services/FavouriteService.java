package storysflower.com.storysflower.services;

/**
 * @author ntynguyen
 */
public interface FavouriteService {
    boolean getFavouriteByUserId(Long productId, Long id);

    boolean updateFavourite(Long productId, Long id, boolean isFavourite);
}
