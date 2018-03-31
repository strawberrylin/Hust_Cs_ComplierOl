package me.wlins.wloj_back.repository;
/*
 * @Author: Strawberrylin
 * @Description: extends from JpaRepository, save/findAll/finaOne/delete...
 * @Date: Created in 下午8:18 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
    /*
     * @param username
     * @Description: find user by username
     * @Date: 下午8:20 18-3-31
     */
    User findByUsername(String username);
}
