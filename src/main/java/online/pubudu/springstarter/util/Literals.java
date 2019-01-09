package online.pubudu.springstarter.util;

/**
 * String Literals.
 * @author pubudu welagedara
 * @see <a href="http://pubudu.online">pubudu.online</a>
 */
public final class Literals {

    private Literals() {
    }

    /**
     * Used for Status Code 401 Unauthorized
     */
    public static final String EXCEPTION_AUTHENTICATION_FAILED = "Authentication Failed";

    /*
    * Swagger Documentation
    * */

    // Responses
    public static final int RESPONSE_OK_VALUE = 200;
    public static final String RESPONSE_OK_MESSAGE = "OK";

    public static final int RESPONSE_ERROR_VALUE = 400;
    public static final String RESPONSE_ERROR_MESSAGE = "Bad Request";

    // Controllers
    public static final String SAMPLE_CONTROLLER_TAG = "Sample Controller Tag";
    public static final String SAMPLE_CONTROLLER_DESCRIPTION = "Sample Controller Description";

    // DTOs
    public static final String LOGIN_DTO_USERNAME_EXAMPLE = "admin";
    public static final String LOGIN_DTO_PASSWORD_EXAMPLE = "admin";

    public static final String TOKEN_DTO_TOKEN_EXAMPLE = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImp0aSI6IjMwMWEzYTZiLWJlYTQtNDVlNC04Zjg5LTZlNzc4ZjAyNGY0OSIsImlhdCI6MTU0NjQ2NjAyNywiZXhwIjoxNTU0NTU0OTE1LCJzY29wZXMiOlsiUk9MRV9BRE1JTiIsIlJPTEVfVVNFUiJdfQ.nMigS6TnKOgattQJASD-W0xMwtoHt8cYvDEuCox8MsY";

    public static final String MESSAGE_DTO_MESSAGE_EXAMPLE = "Subscribe to PewDiePie...!!!";

    public static final String ERROR_DTO_ERROR_EXAMPLE = "401";
    public static final String ERROR_DTO_MESSAGE_EXAMPLE = "Authentication Failed";
}