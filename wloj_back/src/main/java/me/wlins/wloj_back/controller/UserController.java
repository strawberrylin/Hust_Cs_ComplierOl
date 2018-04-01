package me.wlins.wloj_back.controller;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午9:35 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.RestResult;
import me.wlins.wloj_back.entity.User;
import me.wlins.wloj_back.service.UserService;
import me.wlins.wloj_back.util.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final ResultGenerator resultGenerator;

    @Autowired
    public UserController(UserService userService, ResultGenerator resultGenerator) {
        this.userService = userService;
        this.resultGenerator = resultGenerator;
    }

    @PostMapping("/login")
    public RestResult login(@NotNull(message = "Username is required") String username, @NotNull(message = "Password is required") String password, HttpSession session) {
        User user = userService.checkLogin(username, password);
        if(user != null){
            // store in session
            session.setAttribute("user", user);
            return resultGenerator.getSuccessResult("Login Sucessfully", user);
        }
        return resultGenerator.getFailResult("Incorrect username/password");
    }

    // param vertify
    @ExceptionHandler(ConstraintViolationException.class)
    public RestResult handleConstraintViolationException(ConstraintViolationException cve) {
        String errorMessage = cve.getConstraintViolations().iterator().next().getMessage();
        return resultGenerator.getFailResult(errorMessage);
    }

    // primary key
    @ExceptionHandler(DataIntegrityViolationException.class)
    public RestResult handleDataIntegrityViolationException(DataIntegrityViolationException exception) {
        return resultGenerator.getFailResult("Unique Primary Key");
    }
}
