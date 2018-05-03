package me.wlins.wloj_back.service;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午3:35 18-4-25
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Record;

import java.util.ArrayList;
import java.util.List;

public interface RecordService {
    /*
     * @param record
     * @Description: 
     * @Date: 下午3:36 18-4-25
     */
    Record saveRecord(Record record);
    /*
     * @param usernum
     * @Description:
     * @Date: 下午7:43 18-4-27
     */
    ArrayList<Record> findRecordsByIdUsernum(String usernum);
    /*
     * @param labnum
     * @Description:
     * @Date: 下午7:43 18-4-27
     */
    ArrayList<Record> findRecordsByIdLabnum(int labnum);

    /*
     * @param usernum
     * @Description:
     * @Date: 下午5:12 18-5-3
     */
    int updateScore(int score, String usernum, int labnum);
}
