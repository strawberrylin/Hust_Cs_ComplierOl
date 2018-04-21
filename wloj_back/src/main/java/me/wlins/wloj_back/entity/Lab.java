package me.wlins.wloj_back.entity;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午4:15 18-4-18
 * @Modified By:
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lab {
    @Id
    @NotNull(message = "lab num is required")
    @Column(unique=true, nullable=false)
    private int labNum;

    @NotNull(message = "lab name is required")
    private String labName;

    @NotNull(message = "lab content is required")
    private String labPath;

    @NotNull(message = "input is required")
    private String inputPath;

    @NotNull(message = "output is requred")
    private String outputPath;

    public Lab() {
    }

    public Lab(@NotNull(message = "lab num is required") int labNum, @NotNull(message = "lab name is required") String labName, @NotNull(message = "lab content is required") String labPath, @NotNull(message = "input is required") String inputPath, @NotNull(message = "output is requred") String outputPath) {
        this.labNum = labNum;
        this.labName = labName;
        this.labPath = labPath;
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }

    public int getLabNum() {
        return labNum;
    }

    public String getLabName() {
        return labName;
    }

    public String getLabPath() {
        return labPath;
    }

    public String getInputPath() {
        return inputPath;
    }

    public String getOutputPath() {
        return outputPath;
    }

    public void setLabNum(int labNum) {
        this.labNum = labNum;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public void setLabPath(String labPath) {
        this.labPath = labPath;
    }

    public void setInputPath(String inputPath) {
        this.inputPath = inputPath;
    }

    public void setOutputPath(String outputPath) {
        this.outputPath = outputPath;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "id.lab")
    private Set<Record> records = new HashSet<Record>();
}
