package domain;

/**
 * Created by Fan on 2017/3/4.
 */
public enum ResultType {
    SUCCESS("success"),
    FAIL("fail"),
    ERROR("error");

    private String value;

    ResultType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
