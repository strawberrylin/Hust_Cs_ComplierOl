package me.wlins.wloj_back.controller;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午5:01 18-4-27
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Record;
import me.wlins.wloj_back.entity.RestResult;
import me.wlins.wloj_back.service.RecordService;
import me.wlins.wloj_back.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.lang.reflect.Array;
import java.util.ArrayList;

@RestController
@RequestMapping("/record")
public class RecordController {
    private final ResultGenerator resultGenerator;
    private final RecordService recordService;

    @Autowired
    public RecordController(ResultGenerator resultGenerator, RecordService recordService){
        this.recordService = recordService;
        this.resultGenerator = resultGenerator;
    }

    @PostMapping("/personal")
    public RestResult getPersonalRecords(@NotNull(message = "UserNum is required") String usernum){
        ArrayList<Record> records = recordService.findRecordsByIdUsernum(usernum);
        return resultGenerator.getSuccessResult("Lab Records", records);
    }

    @PostMapping("/detail")
    public RestResult getRecordDetail(@NotNull(message = "CodePath is required") String codepath,
                                      @NotNull(message = "ResultPath is required") String outputpath){
        ArrayList<String> result = new ArrayList<String>();
        result.add(readFile(codepath));
        result.add(readFile(outputpath));
        if(result != null){
            return resultGenerator.getSuccessResult(result);
        }
        return resultGenerator.getFailResult("Read file failed.");
    }

    public String readFile(String filePath){
        return LabAdminController.getFile(filePath).toString();
    }
}
