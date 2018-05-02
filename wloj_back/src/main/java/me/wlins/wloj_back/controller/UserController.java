package me.wlins.wloj_back.controller;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午9:35 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.RestResult;
import me.wlins.wloj_back.entity.User;
import me.wlins.wloj_back.service.LabService;
import me.wlins.wloj_back.service.UserService;
import me.wlins.wloj_back.util.ResultGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.util.List;


@RestController
@RequestMapping("/user")
@Validated
public class UserController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final UserService userService;
    private final ResultGenerator resultGenerator;

    @Autowired
    public UserController(UserService userService, LabService labService, ResultGenerator resultGenerator) {
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

    @PostMapping("/update")
    public RestResult update(@NotNull(message = "UserNum is required") String usernum,
                             @NotNull(message = "OldPassword is required") String oldpassword,
                             @NotNull(message = "NewPassword is required") String newpassword){
        int x = userService.updatePassword(newpassword, usernum, oldpassword);
        if(x == 0) {
            return resultGenerator.getSuccessResult("Update successfully!");
        }
        return resultGenerator.getFailResult("Update failed!", x);
    }

    @PostMapping("/add")
    public RestResult add(@NotNull(message = "UserNum is required") String userNum,
                          @NotNull(message = "UserName is required") String userName,
                          @NotNull(message = "Password is reuird") String password,
                          @NotNull(message = "Type is reqired") int type) {
        User user = new User(userNum, userName, password, type);
        User ru = userService.saveUser(user);
        if(ru != null){
            return resultGenerator.getSuccessResult("Add successfully", ru);
        }
        return resultGenerator.getFailResult("Add failed");
    }

    @GetMapping("/list")
    public RestResult getUserList(){
        List<User> userList = userService.findAll();
        if(userList != null){
            return resultGenerator.getSuccessResult("Query Sucessfully", userList);
        }
        return resultGenerator.getFailResult("Query failed");
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
