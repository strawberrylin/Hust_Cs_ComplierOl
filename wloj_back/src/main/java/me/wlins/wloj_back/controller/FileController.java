package me.wlins.wloj_back.controller;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 上午10:46 18-4-10
 * @Modified By:
 */

import me.wlins.wloj_back.entity.RestResult;
import me.wlins.wloj_back.util.ResultGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/file")
@Validated

public class FileController {

    private final ResultGenerator resultGenerator;

    @Autowired
    public FileController(ResultGenerator resultGenerator){
        this.resultGenerator = resultGenerator;
    }

    @PostMapping(value = "/upload")
    public RestResult doUploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file,
                                   @RequestParam("curouse") String curouse,
                                   @RequestParam("username") String username, ModelMap map){
        if (!file.isEmpty()){
            String path = request.getServletContext().getRealPath("file");
            String fileName = file.getOriginalFilename();
            File filePath = new File(path, fileName);
            if(!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdir();
            }

            try{
                List<String> processListCE = new ArrayList<String>();
                List<String> processListCI = new ArrayList<String>();
                List<String> output = new ArrayList<String>();
                processListCE.add("COMPLIE OUTPUT ERROR :");
                processListCI.add("COMPLIE OUTPUT :");
                file.transferTo(new File(path + File.separator +fileName));
                String str = path + File.separator +fileName;
                String[] cmdC = {"gcc","-g", "-Wall" ,str ,"-o","test"};
                System.out.println(filePath.getAbsolutePath());
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
                for(String l : processListCE){
                    System.out.println(l);
                    output.add(l);
                }
                for(String l : processListCI){
                    System.out.println(l);
                    output.add(l);
                }
                map.put("errorC", processListCE);
                map.put("inputC", processListCI);
                if(0 == exitValueC){
                    List<String> processListXE = new ArrayList<String>();
                    List<String> processListXI = new ArrayList<String>();
                    processListXE.add(" RUN OUTPUT ERROR :");
                    processListXI.add(" RUN OUTPUT :");
                    String[] cmdX = {"./test"};
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

                    for(String l : processListXE){
                        System.out.println(l);
                        output.add(l);
                    }
                    for(String l : processListXI){
                        System.out.println(l);
                        output.add(l);
                    }
                    map.put("errorX", processListXE);
                    map.put("inputX", processListXI);
                    if(0 == exitValueX){
                        return resultGenerator.getSuccessResult("Sucessfully",output);
                    }else{
                        return resultGenerator.getFailResult("Sorry there is some problem with run",output);
                    }
                }else{
                    return resultGenerator.getFailResult("Sorry there is some problem with compiler",output);
                }
            }catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return resultGenerator.getFailResult("Sorry there is some problem");
    }
}
