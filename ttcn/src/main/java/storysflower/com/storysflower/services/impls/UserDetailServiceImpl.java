package storysflower.com.storysflower.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import storysflower.com.storysflower.config.security.CustomUserDetail;
import storysflower.com.storysflower.model.tables.tables.records.UserRecord;
import storysflower.com.storysflower.repositories.UserRepository;

/**
 * @author ntynguyen
 */
@Component(value = "userDetailService")
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserRecord userRecord = userRepository.findByEmail(email);
        if (userRecord == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new CustomUserDetail(userRecord.getId(), email, userRecord.getPassword(), userRecord.getFirstname(), userRecord.getLastname(), userRecord.getImageId(), userRepository.getAuthority(email));
    }
}
