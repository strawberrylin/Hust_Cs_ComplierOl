package me.wlins.wloj_back.service;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午8:17 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.User;
import me.wlins.wloj_back.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User checkLogin(String usernum, String password){
        return userRepository.findFirstByUsernumAndPassword(usernum, password);
    }

    @Override
    public User findByUsernum(String usernum){
        return userRepository.findByUsernum(usernum);
    }
}
