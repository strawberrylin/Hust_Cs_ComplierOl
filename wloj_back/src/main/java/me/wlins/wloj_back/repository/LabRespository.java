package me.wlins.wloj_back.repository;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午6:12 18-4-21
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface LabRespository extends JpaRepository<Lab, Integer>{
    /*
     * @param find all the labs
     * @Description:
     * @Date: 下午3:25 18-4-25
     */
    ArrayList<Lab> findAll();
    /*
     * @param find one lab
     * @Description:
     * @Date: 下午3:25 18-4-25
     */
    Lab findLabByLabNum(int labNum);
}
