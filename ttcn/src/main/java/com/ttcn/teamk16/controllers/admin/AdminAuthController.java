package storysflower.com.storysflower.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import storysflower.com.storysflower.constants.UrlConstants;
import storysflower.com.storysflower.dto.UserDTO;
import storysflower.com.storysflower.services.OccasionService;
import storysflower.com.storysflower.services.UserService;
import storysflower.com.storysflower.utils.AuthUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping(UrlConstants.URL_ADMIN)
public class AdminAuthController {
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserService userService;
    @GetMapping(UrlConstants.URL_LOGIN)
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("userLogin")!= null){
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_INDEX;
        }
        return  "admin/login";
    }
    @PostMapping(UrlConstants.URL_LOGIN)
    public String login(@ModelAttribute("userDTO") UserDTO userDTO,
                        HttpServletRequest request, RedirectAttributes redirec) {
        System.out.println(userDTO);
        if(userDTO == null ){
            redirec.addFlashAttribute("msg", "The email or phone number you entered does not match any accounts");
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        if("".equals(userDTO.getEmail()) ||"".equals(userDTO.getPassWord())) {
            redirec.addFlashAttribute("msg", "The email or phone number you entered does not match any accounts");
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        if(userService.login(userDTO)){
            if("CUS".equals(userService.findUserByEmail(userDTO.getEmail()).getRole())){
                redirec.addFlashAttribute("msg", "The email or phone number you entered does not match any accounts");
                return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
            }
            UserDTO userLogin = userService.findUserByEmail(userDTO.getEmail());
            HttpSession session = request.getSession();
            session.setAttribute("userLogin", userLogin);
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_INDEX;
        }else {
            redirec.addFlashAttribute("msg", "The email or phone number you entered does not match any accounts");
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
    }
    @GetMapping(UrlConstants.URL_LOGOUT)
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("userLogin");
        return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
    }

    @GetMapping(UrlConstants.URL_403_)
    public String error403(HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        return "admin/error404";
    }

    @GetMapping(UrlConstants.URL_404_)
    public String error404(HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        return "admin/error404";
    }
}
