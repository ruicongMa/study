package cn.rpc;

import java.io.Serializable;
import java.util.Arrays;

/**
 * @author Mark
 * @date 2020/11/1 17:55
 */
public class Request implements Serializable {

    private static final long serialVersionUID = 5994894537039022234L;

    private Class clazz;
    private String methodName;
    private Class[] types;
    private Object[] args;

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getTypes() {
        return types;
    }

    public void setTypes(Class[] types) {
        this.types = types;
    }

    public Object[] getArgs() {
        return args;
    }

    public void setArgs(Object[] args) {
        this.args = args;
    }

    @Override
    public String toString() {
        return "Request{" +
                "clazz=" + clazz +
                ", methodName='" + methodName + '\'' +
                ", types=" + Arrays.toString(types) +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
