package 异常.exception;

/**
 * @author jiabin.
 * @data 18-12-17.
 */
public enum ExceptionCode {
    PROCESS("100"),
    ORDER("200"),
    TASK("300"),
    NODE("400");

    private String code;

    ExceptionCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
