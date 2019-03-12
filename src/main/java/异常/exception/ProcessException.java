package 异常.exception;

/**
 * @author jiabin.
 * @data 18-12-17.
 */
public class ProcessException extends RuntimeException {

    private String code;

    private String msg;

    public ProcessException(ExceptionCode code, String msg) {
        this.code = code.getCode();
        this.msg = msg;
    }
}
