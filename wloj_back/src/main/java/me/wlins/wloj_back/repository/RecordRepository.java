package me.wlins.wloj_back.repository;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午3:34 18-4-25
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record, String>{
    ArrayList<Record> findRecordsByMainKey_User_Usernum(String usernum);

    ArrayList<Record> findRecordsByMainKey_Lab_LabNum(int labnum);

    @Modifying
    @Query(value = "update record r set r.score = ?1, r.state = 1 where r.usernum = ?2 and r.labnum = ?3", nativeQuery = true)
    @Transactional
    int updateScore(int score, String usernum, int labnum);

    @Modifying
    @Query(value = "update record r set r.comments = ?1, r.commentt = ?2 where r.usernum = ?3 and r.labnum = ?4", nativeQuery = true)
    @Transactional
    int updateComment(String comments, String commentt, String usernum, int labnum);
}
