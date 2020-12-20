package storysflower.com.storysflower.services;

import storysflower.com.storysflower.config.security.CustomUserDetail;
import storysflower.com.storysflower.dto.UserDTO;
import storysflower.com.storysflower.dto.UserProfileDTO;

import java.util.List;

/**
 * @author ntynguyen
 */
public interface UserService {
    public CustomUserDetail getUser();

    int countPagination();

    public boolean registerNewUserAccount(UserProfileDTO userProfileDTO);

    List<UserDTO> findAll();

    UserProfileDTO findCustomerByIdUser(Long id);

    boolean addUser(UserDTO userDTO);

    UserDTO findUserByIdUser(Long id);

    UserDTO findUserByEmail(String email);

    String getFullNameById(Long id);

    boolean edit(UserDTO userDTO);

    boolean del(Long id);

    boolean login(UserDTO userDTO);
}
