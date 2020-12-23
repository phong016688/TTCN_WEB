package storysflower.com.storysflower.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import storysflower.com.storysflower.dto.UserDTO;

@Component
public class RePassWordValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(UserDTO.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserDTO userDTO = (UserDTO) object;
        if (!userDTO.getPassWord().equals(userDTO.getRePassWord())) {
            errors.rejectValue("rePassWord", null, "Mật khẩu nhập không khớp");
        }
    }
}
