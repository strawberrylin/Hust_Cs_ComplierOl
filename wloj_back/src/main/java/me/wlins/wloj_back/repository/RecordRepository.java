package me.wlins.wloj_back.repository;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午3:34 18-4-25
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Record;
import me.wlins.wloj_back.key.MainKey;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, String>{
    List<Record> findRecordsByMainKey_User_Usernum(String usernum);

    List<Record> findRecordsByMainKey_Lab_LabNum(int labnum);
}
