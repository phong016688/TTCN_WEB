package storysflower.com.storysflower.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AuthUtil {
    public static boolean isLogin(HttpServletRequest request) throws Exception{
        HttpSession session = request.getSession();
        if(session.getAttribute("userLogin")==null) {
            return false;
        }
        return true;
    }
}
