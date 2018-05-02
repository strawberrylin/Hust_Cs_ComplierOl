package me.wlins.wloj_back.service;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午6:19 18-4-21
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Lab;
import me.wlins.wloj_back.repository.LabRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;

@Service
public class LabServiceImpl implements LabService{
    @Autowired
    LabRespository labRespository;

    @Override
    public Lab saveLab(Lab lab){
        return labRespository.save(lab);
    }

    @Override
    public ArrayList<Lab> findAll(){
        return labRespository.findAll();
    }

    @Override
    public Lab findLabByLabNum(int labNum){
        return labRespository.findLabByLabNum(labNum);
    }

    @Override
    public int updateLab(String labName, int labNum, String labPath, String inputPath, String outputPath){
        return labRespository.updateLab(labName, labNum, labPath, inputPath, outputPath);
    }

    @Override
    public int deleteLabByLabNum(int labNum){
        return labRespository.deleteLabByLabNum(labNum);
    }
}
