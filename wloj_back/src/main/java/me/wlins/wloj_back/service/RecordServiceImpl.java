package me.wlins.wloj_back.service;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午3:36 18-4-25
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Record;
import me.wlins.wloj_back.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService{
    @Autowired
    RecordRepository recordRepository;

    @Override
    public Record saveRecord(Record record){
        return recordRepository.save(record);
    }

    @Override
    public ArrayList<Record> findRecordsByIdUsernum(String usernum){
        return recordRepository.findRecordsByMainKey_User_Usernum(usernum);
    }

    @Override
    public ArrayList<Record> findRecordsByIdLabnum(int labnum){
        return recordRepository.findRecordsByMainKey_Lab_LabNum(labnum);
    }

    @Override
    public int updateScore(int score, String usernum, int labnum){
        return recordRepository.updateScore(score, usernum, labnum);
    }

    @Override
    public int updateComment(String comments, String commentt, String usernum, int labnum){
        return recordRepository.updateComment(comments, commentt, usernum, labnum);
    }
}
