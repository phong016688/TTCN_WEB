package storysflower.com.storysflower.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import storysflower.com.storysflower.dto.UserDTO;
import storysflower.com.storysflower.utils.CharacterUtil;

@Component
public class NameUserValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.isAssignableFrom(UserDTO.class);
    }

    @Override
    public void validate(Object object, Errors errors) {
        UserDTO userDTO = (UserDTO) object;
        if (CharacterUtil.isValidCharacterSpecial(userDTO.getFirstName())) {
            errors.rejectValue("firstName", null, "Vui lòng không nhập các kí tự đặt biệt." + CharacterUtil.show());
        }
        if (CharacterUtil.isValidCharacterSpecial(userDTO.getLastName())) {
            errors.rejectValue("firstName", null, "Vui lòng không nhập các kí tự đặt biệt." + CharacterUtil.show());
        }
    }
}
