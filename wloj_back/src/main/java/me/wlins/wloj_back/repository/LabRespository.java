package me.wlins.wloj_back.repository;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午6:12 18-4-21
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Lab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

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

    /*
     * @param labNum
     * @param labName
     * @Description: 
     * @Date: 上午11:54 18-5-2
     */
    @Modifying
    @Query(value = "update lab l set l.lab_name = ?1, l.lab_path = ?3, l.input_path = ?4, l.output_path = ?5  where l.lab_num = ?2", nativeQuery = true)
    @Transactional
    int updateLab(String labName, int labNum, String labPath, String inputPath, String outputPath);

    /*
     * @param labNum
     * @Description: 
     * @Date: 下午12:53 18-5-2
     */
    @Transactional
    int deleteLabByLabNum(int labNum);
}
