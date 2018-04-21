package me.wlins.wloj_back.controller;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午5:05 18-4-18
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Lab;
import me.wlins.wloj_back.entity.RestResult;
import me.wlins.wloj_back.service.LabService;
import me.wlins.wloj_back.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/lab")
public class LabAdminController {
    private final ResultGenerator resultGenerator;
    private final LabService labService;

    @Autowired
    public LabAdminController(LabService labService, ResultGenerator resultGenerator) {
        this.resultGenerator = resultGenerator;
        this.labService = labService;
    }

    @PostMapping("/add")
    public RestResult addLab(@NotNull(message = "LabNum is required") int labNum,
                             @NotNull(message = "LabName is required") String labName,
                             @NotNull(message = "LabContent is required") String labContent,
                             @NotNull(message = "LabInput is required") String labInput,
                             @NotNull(message = "LabOutput is required") String labOutput
                             ) {
        Lab lab = new Lab(labNum,labName,labContent,labInput,labOutput);
        Lab lo = labService.saveLab(lab);
        if(lo != null){
            return resultGenerator.getSuccessResult("Add successful", labName);
        }
        return resultGenerator.getFailResult("Add successful", labName);
    }
}
