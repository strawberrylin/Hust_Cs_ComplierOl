package me.wlins.test;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 上午8:04 18-3-26
 * @Modified By:
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/persons")
    public List<Person> personList(){
        /*
         * @param
         * @Description: 查询列表
         * @Date: 上午8:46 18-3-26
         */
        return personRepository.findAll();
    }

    @PostMapping(value = "/persons")
    public Person addPerson(@RequestParam("size") String size,
                            @RequestParam("age") Integer age){
        /*
         * @param size
         * @param age
         * @Description:  Add a person
         * @Date: 上午11:14 18-3-26
         */
        Person person = new Person();
        person.setSize(size);
        person.setAge(age);
        return personRepository.save(person);
    }

    @GetMapping(value = "/persons/{id}")
    public Optional personFindOne(@PathVariable("id") Integer id){
        /*
         * @param id
         * @Description: 两种方式可选 注册，返回类是Optional<Girl>， Optional是jdk8自带的 \
         * @GetMapping(value = "/girls/{id}")\
         * public Optional<Girl> girlFindOne(@PathVariable("id") Integer id) {\
         * //直接用findById查找 \
         * //return  girlRepository.findById(id); \
         * //构通Example查找 \
         * Girl girl = new Girl(); \
         * girl.setId(id); \
         * Example<Girl> example = Example.of(girl); \
         * return girlRepository.findOne(example); \

         * @Date: 下午7:39 18-3-26
         */
        return personRepository.findById(id);
    }

    @PutMapping(value = "/persons/{id}")
    public Person personUpdate(@PathVariable("id") Integer id,
                             @RequestParam("age") Integer age,
                             @RequestParam("size") String size){
        /*
         * @param id
         * @param age
         * @param size
         * @Description: save接口根据数据库中的主键来更新,使用Put方式时,Postman要使用x-www-form-urlencode的数据格式
         * @Date: 下午7:55 18-3-26
         */
        Person person = new Person();
        person.setId(id);
        person.setAge(age);
        person.setSize(size);

        return personRepository.save(person);
    }

    @DeleteMapping(value = "/persons/{id}")
    public void personDelete(@PathVariable("id") Integer id){
        /*
         * @param id
         * @Description:
         * @Date: 下午8:04 18-3-26
         */
        personRepository.deleteById(id);
    }

    @GetMapping(value = "persons/age/{age}")
    public List<Person> personListByAge(@PathVariable("age") Integer age){
        return personRepository.findByAge(age);
    }

    @PostMapping(value = "/persons/two")
    public void personTwo(){
        personService.insertTwo();
    }
}
