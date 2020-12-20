package storysflower.com.storysflower.controllers.api;

import org.jooq.tools.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import storysflower.com.storysflower.dto.ImageDTO;
import storysflower.com.storysflower.exceptions.ApiException;
import storysflower.com.storysflower.services.ImageService;
import storysflower.com.storysflower.services.UserService;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

/**
 * @author ntynguyen
 */
@RestController
@RequestMapping("api/image")
public class ImageRestController extends BaseRestController {

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public ResponseEntity<byte[]> getImageById(@PathVariable("id") long id) {
        ImageDTO imageDTO = imageService.findImageById(id);

        if (imageDTO == null) {
            throw new ApiException(NOT_FOUND, "Image not found.");
        }

        return ResponseEntity.ok()
                .cacheControl(CacheControl.maxAge(3600 * 24 * 30, TimeUnit.SECONDS))
                .body(imageDTO.getImage());
    }
    @PostMapping("/product/{productId}")
    public Object addImage(@PathVariable("productId") long productId,
                           @RequestParam("image_file") MultipartFile photo, @RequestParam("image_id") Long oldImageId) throws IOException {
        byte[] imageData = photo.getBytes();
        Long imageId;
        System.out.println(imageData + "imagedata" + productId + "productId");
        imageId = imageService.addProductImage(productId, oldImageId, imageData);
        System.out.println("Image id: " + imageId);

        if (imageId == null) {
            throw new ApiException(BAD_REQUEST, "Server error.");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imageId", imageId);

        return jsonObject;
    }

    /*@PutMapping("/activity/{activity_id}")
    public Object updateImage(@PathVariable("activity_id") long activityId,
                              @RequestParam("image_file") MultipartFile photo,
                              @RequestParam("image_id") Long imageId) throws IOException {

        byte[] imageData = photo.getBytes();
        Long newImageId = imageService.updateActivityImage(activityId, imageId, imageData);
        if (newImageId == 0) {
            throw new ApiException(BAD_REQUEST, "Server error.");
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("imageId", newImageId);
        return jsonObject;
    }*/
}