package storysflower.com.storysflower.controllers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import storysflower.com.storysflower.dto.ApiResponse;
import storysflower.com.storysflower.exceptions.ApiException;

@RestControllerAdvice
public class BaseRestController {
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse> apiExceptionHandler(final ApiException ex) {
        return ResponseEntity.status(ex.getResponseStatus()).body(ApiResponse.failed(ex.getMessage()));
    }

    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ApiResponse maxUploadSizeExceptionHandler(final MaxUploadSizeExceededException max) {
        return ApiResponse.failed("Update failed! Your image's size is exceeds the configured maximum (10MB)");
    }
}
