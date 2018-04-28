package me.wlins.wloj_back.entity;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午5:05 18-4-18
 * @Modified By:
 */

import me.wlins.wloj_back.key.MainKey;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
public class Record {
    @EmbeddedId
    private MainKey mainKey;

    @Column
    private String recordPath;

    @Column
    private String resultPath;

    @Column
    private String date;

    @Column
    private int score;

    @Column
    private int state;

    public MainKey getMainKey() {
        return mainKey;
    }

    public String getRecordPath() {
        return recordPath;
    }

    public String getResultPath() {
        return resultPath;
    }

    public String getDate() {
        return date;
    }

    public void setMainKey(MainKey mainKey) {
        this.mainKey = mainKey;
    }

    public void setRecordPath(String recordPath) {
        this.recordPath = recordPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getScore() {
        return score;
    }

    public int getState() {
        return state;
    }

    public Record() {
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Record(MainKey mainKey, String recordPath, String resultPath, String date, int score, int state) {
        this.mainKey = mainKey;
        this.recordPath = recordPath;
        this.resultPath = resultPath;
        this.date = date;
        this.score = score;
        this.state = state;
    }
}
