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

@Service
public class LabServiceImpl implements LabService{
    @Autowired
    LabRespository labRespository;

    @Override
    public Lab saveLab(Lab lab){
        return labRespository.save(lab);
    }
}
