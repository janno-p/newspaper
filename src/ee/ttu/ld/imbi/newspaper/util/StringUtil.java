package ee.ttu.ld.imbi.newspaper.util;

public class StringUtil {
    public static int parseId(String strId) {
        try {
            return Integer.parseInt(strId);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
