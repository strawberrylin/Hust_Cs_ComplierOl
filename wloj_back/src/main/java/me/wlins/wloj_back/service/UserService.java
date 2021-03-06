package me.wlins.wloj_back.service;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午8:13 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.User;

import java.util.ArrayList;
import java.util.List;

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
    /*
     * @param usernum
     * @Description:
     * @Date: 下午3:21 18-4-25
     */
    User findByUsernum(String usernum);

    /*
     * @param usernum
     * @param oldpassword
     * @param newpassword
     * @Description:
     * @Date: 上午8:25 18-5-2
     */
    int updatePassword(String newpassword, String usernum, String oldpassword);

    /*
     * @param null
     * @Description: query to get all the user
     * @Date: 上午2:40 18-5-3
     */
    List<User> findAll();

    /*
     * @param usernum
     * @Description:
     * @Date: 上午11:36 18-5-3
     */
    int deleteUserByUsernum(String usernum);
}
