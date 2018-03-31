package me.wlins.wloj_back.entity;
/*
 * @Author: Strawberrylin
 * @Description: response code
 * @Date: Created in 下午5:13 18-3-31
 * @Modified By:
 */

public enum ResultCode {
    SUCCESS(200), // success
    FAIL(400), // failure
    UNAUTHORIZED(401), // unauthorized
    NOT_FOUND(400), // not found
    INTERNAL_SERVER_ERROR(500); // inner error

    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
