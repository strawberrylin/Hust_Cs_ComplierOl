package me.wlins.wloj_back.service;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午2:55 18-4-21
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Lab;

import java.util.ArrayList;

public interface LabService {
    /*
     * @param lab
     * @Description: save a lab information
     * @Date: 上午10:11 18-4-25
     */
    Lab saveLab(Lab lab);
    /*
     * @param null
     * @Description: quare all the labs
     * @Date: 上午10:12 18-4-25
     */
    ArrayList<Lab> findAll();
    /*
     * @param labNum
     * @Description: find a lab
     * @Date: 上午10:12 18-4-25
     */
    Lab findLabByLabNum(int labNum);
}
