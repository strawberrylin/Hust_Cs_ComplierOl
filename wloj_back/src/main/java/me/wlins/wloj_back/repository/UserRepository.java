package me.wlins.wloj_back.repository;
/*
 * @Author: Strawberrylin
 * @Description: extends from JpaRepository, save/findAll/finaOne/delete...
 * @Date: Created in 下午8:18 18-3-31
 * @Modified By:
 */

import me.wlins.wloj_back.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface UserRepository extends JpaRepository<User, String>{
    /*
     * @param usernum
     * @Description: find user by username
     * @Date: 下午8:20 18-3-31
     */
    User findFirstByUsernumAndPassword(String usernum, String password);
    /*
     * @param usernum
     * @Description: 
     * @Date: 下午3:23 18-4-25
     */
    User findByUsernum(String usernum);

    /*
     * @param newpassword
     * @param usernum
     * @param oldpwssword
     * @Description:
     * @Date: 上午8:31 18-5-2
     */
    @Modifying
    @Query(value = "update user u set u.password = ?1 where u.usernum = ?2 and u.password = ?3", nativeQuery = true)
    @Transactional
    int updatePassword(String newpassword, String usernum, String oldpassword);
}
