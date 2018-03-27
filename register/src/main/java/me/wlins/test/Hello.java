package me.wlins.test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class Hello {

    @Autowired
    private TestProperties tp ;

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String print(@RequestParam("id") int id){
        return tp.getName() + id;
    }
}
