package storysflower.com.storysflower.constants;




public class UrlConstants {
    public static final String URL_ADMIN = "/admin";
    public static final String URL_LOGIN = "/login";
    public static final String URL_LOGOUT = "/logout";
    public static final String URL_INDEX = "/index";
    public static final String URL_403_ = "/403";
    public static final String URL_404_ = "/404";

    public static final String URL_ADMIN_USER_INDEX = "/user/index";
    public static final String URL_ADMIN_USER_DEL = "/user/del/{id}";
    public static final String URL_ADMIN_USER_EDIT = "/user/edit/{id}";
    public static final String URL_ADMIN_USER_ADD = "/user/add";


    public static final String URL_ADMIN_REVENUE_INDEX = "/revenue/index";
    public static final String URL_ADMIN_REVENUE_DETAIL = "/revenue/detail-{date}";
    public static final String URL_ADMIN_REVENUE_SEARCH = "/revenue/search";
    public static final String URL_ADMIN_REVENUE_DOWNLOAD ="/revenue/download" ;

    public static final String URL_ADMIN_CUSTOMER_INDEX = "/customer/index";
    public static final String URL_ADMIN_CUSTOMER_INDEX_PAGINATION="/customer/index/{page}";
    public static final String URL_ADMIN_CUSTOMER_DETAIL = "/customer/detail/{id}";

    public static final String URL_ADMIN_CART_INDEX = "/cart/index";
    public static final String URL_ADMIN_CART_INDEX_PAGINATION="/cart/index/{page}";
    public static final String URL_ADMIN_CART_DETAIL = "/cart/detail/{id}";
    public static final String URL_ADMIN_CART_ORDER = "/cart/order/{id}";



    public static final String URL_ADMIN_PRODUCT_INDEX_ID= "/product/index/{id}";
    public static final String URL_ADMIN_PRODUCT_INDEX = "/product/index";
    public static final String URL_ADMIN_PRODUCT_POST_EDIT= "/product/{oc}/edit/{id}";
    public static final String URL_ADMIN_PRODUCT_EDIT_ID= "product/edit/{id}";
    public static final String URL_ADMIN_PRODUCT_ADD= "product/add/{occ}";
    public static final String URL_ADMIN_PRODUCT_DEL_OCC_ID= "product/del/occ{occ}/{id}";

}
