package ee.ttu.ld.imbi.newspaper.util;

import javax.servlet.http.HttpServletRequest;

public class StringUtil {
    public static int parseId(String strId) {
        try {
            return Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static String getFullURI(HttpServletRequest request) {
        if (request.getQueryString() != null) {
            return request.getRequestURI() + "?" + request.getQueryString();
        }
        return request.getRequestURI();
    }
}
