package me.wlins.wloj_back.util;
/*
 * @Author: Strawberrylin
 * @Description: a factory to generate message
 * @Date: Created in 下午7:14 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.RestResult;
import me.wlins.wloj_back.entity.ResultCode;
import org.hibernate.hql.internal.ast.tree.RestrictableStatement;
import org.springframework.stereotype.Component;

@Component
public class ResultGenerator {
    private static final String SUCCESS = "success";
    // success
    public RestResult getSuccessResult() {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                ;
    }

    // success with extra data
    public RestResult getSuccessResult(Object data) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(SUCCESS)
                .setData(data);
    }

    // success with own message and data
    public RestResult getSuccessResult(String message, Object data) {
        return new RestResult()
                .setCode(ResultCode.SUCCESS)
                .setMessage(message)
                .setData(data);
    }

    // failed with extra message
    public RestResult getFailResult(String message) {
        return new RestResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message);
    }

    // failed with own message
    public RestResult getFailResult(String message, Object data) {
        return new RestResult()
                .setCode(ResultCode.FAIL)
                .setMessage(message)
                .setData(data);
    }

    // self-define
    public RestResult getFreeResult(ResultCode code, String message, Object data){
        return new RestResult()
                .setCode(code)
                .setMessage(message)
                .setData(data);
    }
}
