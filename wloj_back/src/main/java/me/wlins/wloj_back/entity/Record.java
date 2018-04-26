package me.wlins.wloj_back.entity;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午5:05 18-4-18
 * @Modified By:
 */

import me.wlins.wloj_back.key.MainKey;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Record {
    @EmbeddedId
    private MainKey id;

    @Column
    private String recordPath;

    @Column
    private String resultPath;

    @Column
    private Date recordDate;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(name = "userNum"),
        @JoinColumn(name = "labNum")
    })

    public MainKey getId() {
        return id;
    }

    public String getRecordPath() {
        return recordPath;
    }

    public String getResultPath() {
        return resultPath;
    }

    public Date getRecordDate() {
        return recordDate;
    }

    public void setId(MainKey id) {
        this.id = id;
    }

    public void setRecordPath(String recordPath) {
        this.recordPath = recordPath;
    }

    public void setResultPath(String resultPath) {
        this.resultPath = resultPath;
    }

    public void setRecordDate(Date recordDate) {
        this.recordDate = recordDate;
    }

    public Record() {
    }

    public Record(MainKey id, String recordPath, String resultPath, Date recordDate) {
        this.id = id;
        this.recordPath = recordPath;
        this.resultPath = resultPath;
        this.recordDate = recordDate;
    }


}
