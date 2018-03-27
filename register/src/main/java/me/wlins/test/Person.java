package me.wlins.test;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午10:53 18-3-25
 * @Modified By:
 */


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    private String size;
    private Integer age;

    public Person() {
    }

    public Integer getId() {
        return id;
    }

    public String getSize() {
        return size;
    }

    public Integer getAge() {
        return age;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
