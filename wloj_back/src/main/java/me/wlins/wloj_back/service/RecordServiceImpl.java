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

@Service
public class RecordServiceImpl implements RecordService{
    @Autowired
    RecordRepository recordRepository;

    @Override
    public Record saveRecord(Record record){
        return recordRepository.save(record);
    }
}
