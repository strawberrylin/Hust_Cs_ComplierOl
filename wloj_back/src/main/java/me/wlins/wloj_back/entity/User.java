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
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @NotNull(message = "num is required")
    @Column(unique = true, nullable = false)
    private String usernum; //primary key

    @NotNull(message = "username is required")
    @Column(nullable = false)
    private String username;

    @NotNull(message = "password is required")
    @Column(nullable = false)
    @Size(min = 6, max = 128, message = "6~18　is required")
    private String password;

    @NotNull(message = "type is required")
    @Column(nullable = false)
    private Integer type;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "mainKey.user")
    private Set<Record> records = new HashSet<Record>();

    // seter and geter


    public String getUsernum() {
        return usernum;
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

    public void setUsernum(String usernum) {
        this.usernum = usernum;
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
