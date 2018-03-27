package me.wlins.test;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 上午8:25 18-3-26
 * @Modified By:
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer>{
    public List<Person> findByAge(Integer age);
}
