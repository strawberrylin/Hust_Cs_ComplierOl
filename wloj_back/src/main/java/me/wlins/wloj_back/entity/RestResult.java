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

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
