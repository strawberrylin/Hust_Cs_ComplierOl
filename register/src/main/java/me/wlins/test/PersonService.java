package me.wlins.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午8:34 18-3-26
 * @Modified By:
 */
@Service
public class PersonService {
    /*
     * @param null
     * @Description: Add two person
     * @Date: 下午8:37 18-3-26
     */
    @Autowired
    private PersonRepository personRepository;

    @Transactional
    public void insertTwo(){
        Person personA = new Person();
        personA.setAge(18);
        personA.setSize("F");
        personRepository.save(personA);

        Person personB = new Person();
        personB.setAge(20);
        personB.setSize("E");
        personRepository.save(personB);
    }

}
