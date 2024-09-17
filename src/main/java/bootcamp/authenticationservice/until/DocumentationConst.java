package bootcamp.authenticationservice.until;

public class DocumentationConst {
    public static final String AUTH_CONTROLLER_NAME = "AuthController";
    public static final String AUTH_CONTROLLER_DESCRIPTION = "This controller is responsible for handling the authentication of the user";
    public static final String AUTH_CONTROLLER_LOGIN_DESCRIPTION = "This endpoint is responsible for authenticating the user";
    public static final String CODE_STATUS_201 = "201";
    public static final String CODE_STATUS_403 = "403";
    public static final String DESCRIPTION_STATUS_201_AUTH = "Login successful";
    public static final String DESCRIPTION_STATUS_403_AUTH = "Login failed";
    public static final String USER_CONTROLLER_NAME = "UserController";
    public static final String USER_CONTROLLER_DESCRIPTION = "This controller is responsible for handling the user";
    public static final String USER_CONTROLLER_CREATE_WAREHOUSE_DESCRIPTION = "This endpoint is responsible for creating a warehouse";
    public static final String DESCRIPTION_STATUS_201_USER = "Auxiliary of Warehouse created successful";
    public static final String DESCRIPTION_STATUS_403_USER = "Unauthorized user";
    public static final String CODE_STATUS_401 = "401";
    public static final String DESCRIPTION_STATUS_401_USER = "Unauthorized user";
    public static final String DESCRIPTION_STATUS_400_USER = "User already exists";
    public static final String CODE_STATUS_400 = "400";
    public static final String USER_CONTROLLER_CREATE_CUSTOMER_DESCRIPTION = "This endpoint is responsible for creating a customer";

    private DocumentationConst() {
    }
}
