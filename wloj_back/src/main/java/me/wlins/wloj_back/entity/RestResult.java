package me.wlins.wloj_back.entity;
/*
 * @Author: Strawberrylin
 * @Description: define a entity of rest data
 * @Date: Created in 下午5:10 18-3-31
 * @Modified By:
 */

public class RestResult {
    // status code
    private int code;
    // message
    private String message;
    // extra content
    private Object data;

    public RestResult() {
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getData() {
        return data;
    }

    public RestResult setCode(ResultCode code) {
        this.code = code.getCode();
        return this;
    }

    public RestResult setCode(int code) {
        this.code = code;
        return this;
    }

    public RestResult setMessage(String message) {
        this.message = message;
        return this;
    }

    public RestResult setData(Object data) {
        this.data = data;
        return this;
    }
}
