package storysflower.com.storysflower.utils;

import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ErrorUtil {
    public static List<ObjectError> delError(List<ObjectError> l) {

        List<ObjectError> list = new ArrayList<>();
        for (int i = 0; i < l.size(); i++) {
            if ("Vui lòng nhập mật khẩu".equals(l.get(i).getDefaultMessage()) == false &&
                    "Mật khẩu nhập không khớp".equals(l.get(i).getDefaultMessage()) == false &&
                    "Độ dài dài mật khẩu không tốt!".equals(l.get(i).getDefaultMessage()) == false) {
                System.out.println(l.get(i).getDefaultMessage());
                list.add(l.get(i));
            }
        }
        System.out.println(list.size());
        return list;
    }
}
