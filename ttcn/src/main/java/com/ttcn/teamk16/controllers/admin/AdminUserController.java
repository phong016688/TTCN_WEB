package storysflower.com.storysflower.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import storysflower.com.storysflower.constants.UrlConstants;
import storysflower.com.storysflower.dto.UserDTO;
import storysflower.com.storysflower.services.OccasionService;
import storysflower.com.storysflower.services.ProductService;
import storysflower.com.storysflower.services.RevenueService;
import storysflower.com.storysflower.services.UserService;
import storysflower.com.storysflower.utils.AuthUtil;
import storysflower.com.storysflower.utils.ErrorUtil;
import storysflower.com.storysflower.validator.NameUserValidator;
import storysflower.com.storysflower.validator.RePassWordValidator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping(UrlConstants.URL_ADMIN)
public class AdminUserController {
    @Autowired
    UserService userService;
    @Autowired
    RePassWordValidator rePassWordValidator;
    @Autowired
    NameUserValidator nameUserValidator;
    @Autowired
    RevenueService revenueService;

    private static final String OCCASIONS = "occasions";

    @Autowired
    private OccasionService occasionService;

    @Autowired
    ProductService productService;

    @ModelAttribute
    public void leftbar(Model model){
        model.addAttribute(OCCASIONS, occasionService.findAllOccasion());
    }
    @GetMapping(UrlConstants.URL_INDEX)
    public String ind(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if(session.getAttribute("userLogin")!= null){
            request.setAttribute("numberUser", userService.countPagination());
            request.setAttribute("numberPost", productService.getAllFlower().size());
            return  "admin/index";
        }


        return  "admin/login";
    }
    @GetMapping({UrlConstants.URL_ADMIN_USER_INDEX})
    public String index(Model model, HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        HttpSession session = request.getSession();
        UserDTO userLogin = (UserDTO) session.getAttribute("userLogin");
        List<UserDTO> listUser = userService.findAll();
        model.addAttribute("userLogin", userLogin);
        model.addAttribute("listUser", listUser);
        return "admin/user/index";
    }

    @GetMapping({UrlConstants.URL_ADMIN_USER_EDIT})
    public String edit(Model model, @PathVariable(value = "id", required = false) Long id, HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        HttpSession session = request.getSession();
        UserDTO userDTO = userService.findUserByIdUser(id);
        UserDTO userLogin = (UserDTO) session.getAttribute("userLogin");
        if (userLogin.getRole().equals("ADMIN") || id == userLogin.getId()) {
            model.addAttribute("userDTO", userDTO);
            return "admin/user/edit";
        } else {
            return "admin/error404";
        }
    }

    @PostMapping({UrlConstants.URL_ADMIN_USER_EDIT})
    public String edit(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult rs, Model model, HttpServletRequest request,
                       @PathVariable(value = "id", required = false) Long id, RedirectAttributes redirec) {
        HttpSession session = request.getSession();
        UserDTO userLogin = (UserDTO) session.getAttribute("userLogin");
        userDTO.setId(id);
        userDTO.setEmail(userService.findUserByIdUser(id).getEmail());
        boolean check = true;
        if ("".equals(userDTO.getPassWord())) {
            String pw = userService.findUserByIdUser(id).getPassWord();
            userDTO.setPassWord(pw);
            userDTO.setRePassWord(pw);
            check = false;
        }
        rePassWordValidator.validate(userDTO, rs);
        nameUserValidator.validate(userDTO, rs);
        if (rs.hasErrors()) {
            List<ObjectError> errorList = rs.getAllErrors();
            if (!check) {
                errorList = ErrorUtil.delError(errorList);
            }
            if (errorList.size() != 0) {
                model.addAttribute("userDTO", userService.findUserByIdUser(id));
                model.addAttribute("msg", "Please correct the errors in form!");
                model.addAttribute("rs", rs);
                model.addAttribute("errorList", errorList);
                return "admin/user/edit";
            }
        }
        if (userLogin.getRole().equals("ADMIN")) {
            userDTO.setRole("ROLE_ADMIN");
        } else {
            userDTO.setRole("ROLE_USER");
        }
        if (!userService.edit(userDTO)) {
            /*error*/
        }
        redirec.addFlashAttribute("msg", "Dữ liệu đã được thay đổi");
        return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_USER_INDEX;
    }

    @GetMapping({UrlConstants.URL_ADMIN_USER_DEL})
    public String del(Model model, HttpServletRequest request, RedirectAttributes redirec,
                      @PathVariable(value = "id", required = false) Long id) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        HttpSession session = request.getSession();
        UserDTO userLogin = (UserDTO) session.getAttribute("userLogin");
        if("ADMIN".equals(userLogin.getRole())){
            if (userLogin.getId() == id) return "admin/error404";
            if (!userService.del(id)) {

            }
            redirec.addFlashAttribute("msg", "Dữ liệu đã được xóa một dòng");
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_USER_INDEX;
        }
        return "admin/error404";
    }

    @GetMapping({UrlConstants.URL_ADMIN_USER_ADD})
    public String add(Model model, HttpServletRequest request) throws Exception {
        if(!AuthUtil.isLogin(request)) {
            return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_LOGIN;
        }
        HttpSession session = request.getSession();
        UserDTO userLogin = (UserDTO) session.getAttribute("userLogin");
        if(userLogin.getRole().equals("ADMIN"))
        return "admin/user/add";
        return "admin/error404";
    }

    @PostMapping({UrlConstants.URL_ADMIN_USER_ADD})
    public String add(@Valid @ModelAttribute("userDTO") UserDTO userDTO, BindingResult rs, Model model,
                      RedirectAttributes redirec) {
        rePassWordValidator.validate(userDTO, rs);
        nameUserValidator.validate(userDTO, rs);
        if (rs.hasErrors()) {
            List<ObjectError> errorList = rs.getAllErrors();
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("msg", "Please correct the errors in form!");
            model.addAttribute("rs", rs);
            model.addAttribute("errorList", errorList);
            return "admin/user/add"; // chỉ được dùng return , ko dong redic
        }
        userDTO.setRole("ROLE_USER");
        if (!userService.addUser(userDTO)) {
            model.addAttribute("userDTO", userDTO);
            model.addAttribute("msg", "Error!");
            return "admin/user/index";
        }
        redirec.addFlashAttribute("msg", "Dữ liệu đã được thêm một dòng");
        return "redirect:" + UrlConstants.URL_ADMIN + UrlConstants.URL_ADMIN_USER_INDEX;
    }
}
