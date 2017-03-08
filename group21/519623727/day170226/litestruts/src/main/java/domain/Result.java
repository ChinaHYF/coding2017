package domain;

/**
 * Created by Fan on 2017/3/4.
 */
public class Result {

    private String name;
    private String value;

    public Result() {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Result{" +
                "name='" + name + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
