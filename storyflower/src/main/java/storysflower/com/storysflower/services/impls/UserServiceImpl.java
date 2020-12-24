package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.config.security.CustomUserDetail;
import storysflower.com.storysflower.dto.UserDTO;
import storysflower.com.storysflower.dto.UserProfileDTO;
import storysflower.com.storysflower.model.tables.tables.User;
import storysflower.com.storysflower.repositories.UserRepository;
import storysflower.com.storysflower.services.UserService;

import java.util.List;

/**
 * @author ntynguyen
 */

@Component
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public CustomUserDetail getUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication.getPrincipal() instanceof CustomUserDetail)) {
            return null;
        }
        Object principal = authentication.getPrincipal();
        return (CustomUserDetail) principal;
    }

    @Override
    public boolean registerNewUserAccount(UserProfileDTO userProfileDTO) {
        return userRepository.registerNewUserAccount(userProfileDTO);
    }

    @Override
    public int countPagination() {
        return userRepository.countPagination();
    }

    @Override
    public List<UserDTO> findAll() {
        List<UserDTO> listUser = userRepository.findAll();
        for (UserDTO u : listUser) {
            u.setRole(u.getRole().replace("ROLE_", ""));
            String f = Character.toString(u.getFirstName().charAt(0)).toUpperCase();
            u.setFirstName(f + u.getFirstName().substring(1));
            String l = Character.toString(u.getLastName().charAt(0)).toUpperCase();
            u.setLastName(l + u.getLastName().substring(1));
        }
        return listUser;
    }

    @Override
    public UserProfileDTO findCustomerByIdUser(Long id) {
        return userRepository.findCustomerByIdUser(id);
    }

    @Override
    public boolean addUser(UserDTO userDTO) {
        if (userRepository.addUser(userDTO) == true) {
            return userRepository.addUser_ROLE(userDTO);
        } else {
            System.out.println("false");
            return false;
        }
    }

    @Override
    public String getFullNameById(Long id) {
        UserProfileDTO userProfileDTO = findCustomerByIdUser(id);
        String f = Character.toString(userProfileDTO.getFisrtName().charAt(0)).toUpperCase();
        userProfileDTO.setFisrtName(f + userProfileDTO.getFisrtName().substring(1));
        String l = Character.toString(userProfileDTO.getLastName().charAt(0)).toUpperCase();
        userProfileDTO.setLastName(l + userProfileDTO.getLastName().substring(1));
        return userProfileDTO.getFisrtName() + " " + userProfileDTO.getLastName();
    }

    @Override
    public boolean edit(UserDTO userDTO) {
        if (userRepository.edit(userDTO) == true) {
            return userRepository.edit_ROLE(userDTO);
        } else {
            System.out.println("false");
            return false;
        }
    }

    @Override
    public boolean del(Long id) {
        if (userRepository.del_ROLE(userRepository.getUserByIdUser(id).getEmail())) {
            return userRepository.del(id);
        } else {
            System.out.println("false");
            return false;
        }
    }

    @Override
    public boolean login(UserDTO userDTO) {
        UserDTO user=  userRepository.login(userDTO);
        if(bCryptPasswordEncoder.matches(userDTO.getPassWord(), user.getPassWord())){
            return  true;
        }else {
            return  false;
        }
    }

    @Override
    public UserDTO findUserByIdUser(Long id) {
        UserDTO u = userRepository.getUserByIdUser(id);
        u.setRole(u.getRole().replace("ROLE_", ""));
        return u;
    }

    @Override
    public UserDTO findUserByEmail(String email) {
        UserDTO u = userRepository.getUserByEmail(email);
        u.setRole(u.getRole().replace("ROLE_", ""));
        return u;
    }
}
