package me.wlins.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/home")
public class IndexController {
    @RequestMapping(value="/upload", method= RequestMethod.GET)
    public String showUploadPage(){
        return "code/file";
    }

    @RequestMapping(value = "/doUpload", method = RequestMethod.POST)
    public String doUploadFile(HttpServletRequest request, @RequestParam("file") MultipartFile file, ModelMap map) {
        //Store the file
        if(!file.isEmpty()){
            String path = request.getServletContext().getRealPath("/code");
            String fileName = file.getOriginalFilename();
            File filePath = new File(path, fileName);
            if(!filePath.getParentFile().exists()){
                filePath.getParentFile().mkdirs();
            }

            try{
                List<String> processListCE = new ArrayList<String>();
                List<String> processListCI = new ArrayList<String>();
                processListCE.add("COMPLIE OUTPUT ERROR :");
                processListCI.add("COMPLIE OUTPUT :");
                file.transferTo(new File(path + File.separator +fileName));
                String str = path + File.separator +fileName;
                String[] cmdC = {"gcc","-g", "-Wall" ,str ,"-o","test"};
                System.out.println(filePath.getParentFile());
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
                }
                for(String l : processListCI){
                    System.out.println(l);
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
                    }
                    for(String l : processListXI){
                        System.out.println(l);
                    }
                    map.put("errorX", processListXE);
                    map.put("inputX", processListXI);
                    if(0 == exitValueX){
                        return "success";
                    }else{
                        return "error";
                    }
                }else{
                    return "error";
                }
            }catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "success";
    }
}
