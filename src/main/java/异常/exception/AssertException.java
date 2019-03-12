package 异常.exception;

/**
 * @author jiabin.
 * @data 18-12-17.
 */
public class AssertException {

    public static void isNull(ExceptionCode code, Object object) {
        if (object != null) {
            throw new ProcessException(code, "参数必须为空");
        }
    }

    public static void isNull(ExceptionCode code, Object object, String message) {
        if (object != null) {
            throw new ProcessException(code, message);
        }
    }

    public static void notNull(ExceptionCode code, Object object) {
        if (object == null) {
            throw new ProcessException(code, "参数必须非空");
        }
    }

    public static void notNull(ExceptionCode code, Object object, String message) {
        if (object == null) {
            throw new ProcessException(code, message);
        }
    }

    public static void isTrue(ExceptionCode code, boolean expression, String message) {
        if (!expression) {
            throw new ProcessException(code, message);
        }
    }

    public static void notTrue(ExceptionCode code, boolean expression, String message) {
        isTrue(code, !expression, message);
    }

    public static void equals(ExceptionCode code, String s1, String s2, String message) {
        notNull(code, s1);
        notNull(code, s2);
        if (!s1.equals(s2)) {
            throw new ProcessException(code, message);
        }
    }

    public static void notEquals(ExceptionCode code, String s1, String s2, String message) {
        notNull(code, s1);
        notNull(code, s2);
        if (s1.equals(s2)) {
            throw new ProcessException(code, message);
        }
    }
}
