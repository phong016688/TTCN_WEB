package storysflower.com.storysflower.services;

import storysflower.com.storysflower.dto.ImageDTO;


public interface ImageService {
    public ImageDTO findImageById(Long id);

    Long addProductImage(long productId, Long imageId, byte[] imageData);
}
