package storysflower.com.storysflower.dto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author ntynguyen
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Not Found")
public class NotFoundException extends RuntimeException {
}
