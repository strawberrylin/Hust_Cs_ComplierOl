package me.wlins.wloj_back.entity;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 下午4:23 18-3-31
 * @Modified By:
 */

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //primary key, auto-increament

    @NotNull(message = "username is required")
    @Column(unique = true,nullable = false)
    private String username;

    @NotNull(message = "password is required")
    @Column(nullable = false)
    @Size(min = 6, max = 18, message = "6~18　is required")
    private String password;

    @NotNull(message = "type is required")
    @Column(nullable = false)
    private Integer type;

    // seter an geter


    public Integer getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Integer getType() {
        return type;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
