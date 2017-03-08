package domain;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Fan on 2017/3/4.
 */
public class Action {

    private String name;
    private String classPath;
    private Result success;
    private Result fail;
    private Result error;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassPath() {
        return classPath;
    }

    public void setClassPath(String classPath) {
        this.classPath = classPath;
    }

    public Result getSuccess() {
        return success;
    }

    public void setSuccess(Result success) {
        this.success = success;
    }

    public Result getFail() {
        return fail;
    }

    public void setFail(Result fail) {
        this.fail = fail;
    }

    public Result getError() {
        return error;
    }

    public void setError(Result error) {
        this.error = error;
    }

    public Result getResult(String actionReturnValue) {
        if (StringUtils.equals(actionReturnValue, ResultType.SUCCESS.getValue()))
            return success;
        else if (StringUtils.equals(actionReturnValue, ResultType.FAIL.getValue()))
            return fail;
        else if (StringUtils.equals(actionReturnValue, ResultType.ERROR.getValue()))
            return error;
        else
            throw new RuntimeException("invalid result type");
    }

    public void setResult(String name, Result result) {
        if (StringUtils.equals(name, ResultType.SUCCESS.getValue()))
            success = result;
        else if (StringUtils.equals(name, ResultType.FAIL.getValue()))
            fail = result;
        else if (StringUtils.equals(name, ResultType.ERROR.getValue()))
            error = result;
        else
            throw new RuntimeException("invalid result type");
    }

    @Override
    public String toString() {
        return "Action{" +
                "name='" + name + '\'' +
                ", classPath='" + classPath + '\'' +
                ", success=" + success +
                ", fail=" + fail +
                ", error=" + error +
                '}';
    }
}
