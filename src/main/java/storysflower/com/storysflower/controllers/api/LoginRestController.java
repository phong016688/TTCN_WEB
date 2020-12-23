package storysflower.com.storysflower.controllers.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import storysflower.com.storysflower.dto.NotFoundException;

/**
 * @author ntynguyen
 */
@RestController
@RequestMapping("api/login")
public class LoginRestController {
    @GetMapping()
    public String getLoginPage() {
        throw new NotFoundException();
    }

}
