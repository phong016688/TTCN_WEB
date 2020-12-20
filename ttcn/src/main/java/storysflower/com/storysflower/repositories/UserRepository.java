package storysflower.com.storysflower.repositories;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.dto.UserDTO;
import storysflower.com.storysflower.dto.UserProfileDTO;
import storysflower.com.storysflower.model.tables.tables.records.UserRecord;

import java.util.Collections;
import java.util.List;

import static storysflower.com.storysflower.model.tables.Tables.USER_ROLE;
import static storysflower.com.storysflower.model.tables.tables.User.USER;

/**
 * @author ntynguyen
 */
@Component
public class UserRepository {
    @Autowired
    private DSLContext dslContext;

    public UserRecord findByEmail(String email) {
        System.out.println(USER.EMAIL);
        return dslContext.fetchOne(USER, USER.EMAIL.eq(email));
    }

    public List<SimpleGrantedAuthority> getAuthority(String email) {
        return dslContext.select(USER_ROLE.ROLE)
                .from(USER_ROLE)
                .join(USER).on(USER.EMAIL.eq(USER_ROLE.EMAIL))
                .where(USER.EMAIL.eq(email))
                .fetchInto(SimpleGrantedAuthority.class);
    }

    public boolean registerNewUserAccount(UserProfileDTO userProfileDTO) {
        int id =  dslContext.insertInto(USER)
                .set(USER.FIRSTNAME, userProfileDTO.getFisrtName())
                .set(USER.LASTNAME, userProfileDTO.getLastName())
                .set(USER.EMAIL, userProfileDTO.getEmail())
                .set(USER.PASSWORD, userProfileDTO.getPassword())
                .returning(USER.ID)
                .execute();
        dslContext.insertInto(USER_ROLE)
                .set(USER_ROLE.EMAIL, userProfileDTO.getEmail())
                .set(USER_ROLE.ROLE, "ROLE_CUS")
                .execute();

        return true;

    }

    public int countPagination() {
        return dslContext.selectCount()
                .from(USER)
                .fetchOne(0, Integer.class);
    }

    public List<UserDTO> findAll() {
        List<UserDTO> listUser = dslContext
                .select(USER.ID, USER.LASTNAME.as("lastName"), USER.FIRSTNAME.as("firstName"), USER.PASSWORD.as("passWord"), USER_ROLE.EMAIL, USER_ROLE.ROLE)
                .from(USER)
                .join(USER_ROLE).on(USER.EMAIL.eq(USER_ROLE.EMAIL))
                .orderBy(USER.ID)
                .fetchInto(UserDTO.class);
        return listUser.size() == 0 ? Collections.emptyList() : listUser;
    }

    public UserProfileDTO findCustomerByIdUser(Long id) {
        UserProfileDTO userProfileDTO = dslContext
                .select(USER.ID, USER.FIRSTNAME, USER.LASTNAME, USER.EMAIL, USER.PASSWORD, USER.IMAGE_ID)
                .from(USER)
                .where(USER.ID.eq(id))
                .fetchOneInto(UserProfileDTO.class);
        return userProfileDTO;
    }

    public boolean addUser(UserDTO userDTO) {
        return dslContext.insertInto(USER)
                .set(USER.FIRSTNAME, userDTO.getFirstName())
                .set(USER.LASTNAME, userDTO.getLastName())
                .set(USER.EMAIL, userDTO.getEmail())
                .set(USER.PASSWORD, userDTO.getPassWord())
                .execute() > 0;
    }

    public boolean addUser_ROLE(UserDTO userDTO) {
        return dslContext.insertInto(USER_ROLE)
                .set(USER_ROLE.ROLE, userDTO.getRole())
                .set(USER_ROLE.EMAIL, userDTO.getEmail())
                .execute() > 0;
    }

    public UserDTO getUserByIdUser(Long id) {
        return dslContext.select(USER.ID, USER.LASTNAME.as("lastName"), USER.FIRSTNAME.as("firstName"), USER.PASSWORD.as("passWord"), USER_ROLE.EMAIL, USER_ROLE.ROLE)
                .from(USER)
                .join(USER_ROLE).on(USER.EMAIL.eq(USER_ROLE.EMAIL))
                .where(USER.ID.eq(id))
                .fetchOneInto(UserDTO.class);
    }

    public boolean edit(UserDTO userDTO) {
        return dslContext.update(USER)
                .set(USER.FIRSTNAME, userDTO.getFirstName())
                .set(USER.LASTNAME, userDTO.getLastName())
                .set(USER.PASSWORD, userDTO.getPassWord())
                .where(USER.ID.eq(userDTO.getId()))
                .execute() > 0;
    }

    public boolean edit_ROLE(UserDTO userDTO) {
        return dslContext.update(USER_ROLE)
                .set(USER_ROLE.ROLE, userDTO.getRole())
                .set(USER_ROLE.EMAIL, userDTO.getEmail())
                .where(USER_ROLE.EMAIL.eq(userDTO.getEmail()))
                .execute() > 0;
    }

    public boolean del(Long id) {
        return dslContext.delete(USER)
                .where(USER.ID.eq(id))
                .execute() > 0;
    }

    public boolean del_ROLE(String email) {
        return dslContext.delete(USER_ROLE)
                .where(USER_ROLE.EMAIL.eq(email))
                .execute() > 0;
    }

    public UserDTO getUserByEmail(String email) {
        return dslContext.select(USER.ID, USER.LASTNAME.as("lastName"), USER.FIRSTNAME.as("firstName"), USER.PASSWORD.as("passWord"), USER_ROLE.EMAIL, USER_ROLE.ROLE)
                .from(USER)
                .join(USER_ROLE).on(USER.EMAIL.eq(USER_ROLE.EMAIL))
                .where(USER.EMAIL.eq(email))
                .fetchOneInto(UserDTO.class);
    }

    public UserDTO login(UserDTO userDTO) {
        return dslContext.select(USER.PASSWORD.as("passWord"), USER.EMAIL)
                .from(USER)
                .where(USER.EMAIL.eq(userDTO.getEmail()))
                .fetchOneInto(UserDTO.class);
    }
}
