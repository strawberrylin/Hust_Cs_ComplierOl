package me.wlins.wloj_back.service;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午8:13 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.User;

public interface UserService {
    /*
     * @param user
     * @Description: if register successfully, return the user information, or null
     * @Date: 下午8:14 18-3-31
     */
    User saveUser(User user);
    /*
     * @param usernum
     * @Description: if vertify cuddessfully, return user information, or null
     * @Date: 下午8:15 18-3-31
     */
    User checkLogin(String usernum, String password);
}
