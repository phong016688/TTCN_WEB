package storysflower.com.storysflower.controllers.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import storysflower.com.storysflower.dto.ProductDTO;

import java.util.Arrays;
import java.util.List;

/**
 * @author ntynguyen
 */
@Controller
@RequestMapping("api/test")
public class Test {
    @RequestMapping(
            method = RequestMethod.POST,
            consumes = "application/json")
    public @ResponseBody
    List<String> test(@RequestBody ProductDTO spittle) {
        return Arrays.asList("a", "b");
    }

}
