package storysflower.com.storysflower.services;


public interface FavouriteService {
    boolean getFavouriteByUserId(Long productId, Long id);

    boolean updateFavourite(Long productId, Long id, boolean isFavourite);
}
