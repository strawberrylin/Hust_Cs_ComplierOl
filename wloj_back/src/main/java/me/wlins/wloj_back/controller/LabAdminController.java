package me.wlins.wloj_back.controller;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午5:05 18-4-18
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Lab;
import me.wlins.wloj_back.entity.Record;
import me.wlins.wloj_back.entity.RestResult;
import me.wlins.wloj_back.entity.User;
import me.wlins.wloj_back.key.MainKey;
import me.wlins.wloj_back.service.LabService;
import me.wlins.wloj_back.service.RecordService;
import me.wlins.wloj_back.service.UserService;
import me.wlins.wloj_back.util.ResultGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.io.*;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/lab")
public class LabAdminController {
    private final ResultGenerator resultGenerator;
    private final LabService labService;
    private final UserService userService;
    private final RecordService recordService;

    @Autowired
    public LabAdminController(UserService userService, RecordService recordService,LabService labService ,ResultGenerator resultGenerator) {
        this.resultGenerator = resultGenerator;
        this.labService = labService;
        this.userService = userService;
        this.recordService = recordService;
    }

    @PostMapping("/add")
    public RestResult addLab(@NotNull(message = "LabNum is required") int labNum,
                             @NotNull(message = "LabName is required") String labName,
                             @NotNull(message = "LabContent is required") String labContent,
                             @NotNull(message = "LabInput is required") String labInput,
                             @NotNull(message = "LabOutput is required") String labOutput,
                             HttpServletRequest request
    ) {
        String filePathA = request.getServletContext().getRealPath("/") + labNum + File.separator;
        String filePathLab = saveFile("lab.txt", filePathA, labContent);
        String filePathInput = saveFile("input.txt", filePathA, labInput);
        String filePathOutput = saveFile("output.txt", filePathA, labOutput);
        Lab lab = new Lab(labNum,labName,filePathLab,filePathInput,filePathOutput);
        Lab lo = labService.saveLab(lab);
        if(lo != null){
            return resultGenerator.getSuccessResult("Add successful", filePathLab);
        }
        return resultGenerator.getFailResult("Add failed", filePathLab);
    }

    @GetMapping("/list")
    public RestResult getLabList(){
        ArrayList<Lab> labs = labService.findAll();
        return resultGenerator.getSuccessResult("Login Sucessfully", labs);
    }

    @PostMapping("/compile")
    public RestResult compile(@NotNull(message = "labnum is required") int labNum,
                              @NotNull(message = "username is required") String usernum,
                              @NotNull(message = "code is required") String code,
                              String compile_param,
                              String run_param,
                              HttpServletRequest request
                              ){
        String filePathA = request.getServletContext().getRealPath("/") + labNum + File.separator;
        String recordPath = saveFile("main.c", filePathA, code);
        List<String> outputResult = compile(recordPath, compile_param, run_param);
        StringBuffer compileResult = new StringBuffer();
        for(String l: outputResult){
            compileResult.append(l);
            compileResult.append(System.getProperty("line.separator"));
        }
        String resultPath = saveFile("result.txt",filePathA,compileResult.toString());
        User user = userService.findByUsernum(usernum);
        Lab lab = labService.findLabByLabNum(labNum);
        MainKey mainKey = new MainKey(user,lab);
        Record record = new Record(mainKey,recordPath,resultPath,new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()),0,0);
        Record ro = recordService.saveRecord(record);
        if(ro != null) {
            return resultGenerator.getSuccessResult("Here is the output", outputResult);
        }
        return resultGenerator.getSuccessResult("Faral error");
    }

    @PostMapping("/question")
    public RestResult getQuestion(@NotNull(message = "LabNum is required") int labNum,
                                  HttpServletRequest request){
        Lab lab = labService.findLabByLabNum(labNum);
        String filePath = lab.getLabPath();
        ArrayList<StringBuffer> result = new ArrayList<StringBuffer>();
        StringBuffer labName = new StringBuffer(lab.getLabName());
        result.add(labName);
        result.add(getFile(lab.getLabPath()));
        result.add(getFile(lab.getInputPath()));
        result.add(getFile(lab.getOutputPath()));
        return resultGenerator.getSuccessResult("quare Sucessfully", result);
    }

    @PostMapping("/update")
    public RestResult updateLab(@NotNull(message = "LabNum is required") int labNum,
                                @NotNull(message = "LabName is required") String labName,
                                @NotNull(message = "LabContent is required") String labContent,
                                @NotNull(message = "LabInput is required") String labInput,
                                @NotNull(message = "LabOutput is required") String labOutput,
                                HttpServletRequest request
    ) {
        String filePathA = request.getServletContext().getRealPath("/") + labNum + File.separator;
        String filePathLab = saveFile("lab.txt", filePathA, labContent);
        String filePathInput = saveFile("input.txt", filePathA, labInput);
        String filePathOutput = saveFile("output.txt", filePathA, labOutput);
        int x = labService.updateLab(labName, labNum, filePathLab, filePathInput, filePathOutput);
        if(x == 1){
            return resultGenerator.getSuccessResult("Update sucessfully!");
        }
        return resultGenerator.getFailResult("Update failed!");
    }

    @PostMapping("/delete")
    public RestResult deleteLab(@NotNull(message = "LabNum is required") int labNum) {
        int x = labService.deleteLabByLabNum(labNum);
        System.out.println(x);
        if(x == 1) {
            return resultGenerator.getSuccessResult("Delete successfully");
        }
        return resultGenerator.getFailResult("Delete failed");
    }

    public static String saveFile(String fileName, String filePathA, String fileContent){
        File filePath = new File(filePathA, fileName);
        if (!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdirs();
        }
        try{
            FileOutputStream out = new FileOutputStream(filePathA+fileName);
            out.write(fileContent.getBytes());
            out.flush();
            out.close();
            System.out.println(filePath.getAbsolutePath());
        }catch (IOException e) {
            e.printStackTrace();
        }
        return filePathA+fileName;
    }

    public static StringBuffer getFile(String filePath){
        StringBuffer buffer = new StringBuffer();
        System.out.println(filePath);
        try{
            File fileName = new File(filePath);
            InputStreamReader reader = new InputStreamReader(new FileInputStream(fileName));
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null){
                buffer.append(line);
                buffer.append("<br/>");
            }
            System.out.println(buffer);
        }catch (IOException e){
            e.printStackTrace();
        }
        return buffer;
    }

    public List<String> compile(String recordPath, String compile_param, String run_param){
        File filePath = new File(recordPath);
        List<String> output = new ArrayList<String>();
        if(!filePath.getParentFile().exists()){
            filePath.getParentFile().mkdir();
        }
        try{
            List<String> processListCE = new ArrayList<String>();
            List<String> processListCI = new ArrayList<String>();
            processListCE.add("COMPLIE OUTPUT ERROR :");
            processListCI.add("COMPLIE OUTPUT :");
            String str = recordPath;
            String[] cmdC = {"gcc","-g", "-Wall" ,str ,"-o","test"};
            Process processC = Runtime.getRuntime().exec(cmdC,null,filePath.getParentFile());
            BufferedReader errorC = new BufferedReader(new InputStreamReader(processC.getErrorStream()));
            BufferedReader inputC = new BufferedReader(new InputStreamReader(processC.getInputStream()));
            String line = "";
            int exitValueC, exitValueX;
            while ((line = errorC.readLine()) != null) {
                processListCE.add(line);
            }
            errorC.close();
            while ((line = inputC.readLine()) != null) {
                processListCI.add(line);
            }
            inputC.close();
            exitValueC = processC.waitFor();
            StringBuffer s1 = new StringBuffer();
            for(String l : processListCE){
                System.out.println(l);
                s1.append(l);
            }
            output.add(s1.toString());
            StringBuffer s2 = new StringBuffer();
            for(String l : processListCI){
                System.out.println(l);
                s2.append(l);
            }
            output.add(s2.toString());
            if(0 == exitValueC){
                List<String> processListXE = new ArrayList<String>();
                List<String> processListXI = new ArrayList<String>();
                processListXE.add(" RUN OUTPUT ERROR :");
                processListXI.add(" RUN OUTPUT :");
                String[] cmdX = {"./test",run_param};
                Process processX = Runtime.getRuntime().exec(cmdX,null,filePath.getParentFile());
                BufferedReader errorX = new BufferedReader(new InputStreamReader(processX.getErrorStream()));
                BufferedReader inputX = new BufferedReader(new InputStreamReader(processX.getInputStream()));
                while ((line = errorX.readLine()) != null) {
                    processListXE.add(line);
                }
                errorX.close();

                while ((line = inputX.readLine()) != null) {
                    processListXI.add(line);
                }
                inputX.close();

                exitValueX = processX.waitFor();
                StringBuffer s3 = new StringBuffer();
                for(String l : processListXE){
                    System.out.println(l);
                    s3.append(l);
                }
                output.add(s3.toString());
                StringBuffer s4 = new StringBuffer();
                for(String l : processListXI){
                    System.out.println(l);
                    s4.append(l);
                }
                output.add(s4.toString());
            }
        }catch (IOException e){
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return output;
    }
}
