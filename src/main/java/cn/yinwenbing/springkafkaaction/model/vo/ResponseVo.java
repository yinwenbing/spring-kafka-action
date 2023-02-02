package cn.yinwenbing.springkafkaaction.model.vo;

import java.io.Serializable;

/**
 * 统一的返回对象VO
 */
public class ResponseVo<T> implements Serializable {
    private static final long serialVersionUID = 7748070653645596712L;
    /**
     * 状态码
     */
    private String code;

    /**
     * 状态码对应描述信息
     */
    private String msg;

    /**
     * 数据
     */
    private T data;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
