package me.wlins.wloj_back.key;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 上午10:20 18-4-19
 * @Modified By:
 */

import me.wlins.wloj_back.entity.Lab;
import me.wlins.wloj_back.entity.User;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class MainKey implements Serializable{

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "usernum", nullable = false)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "labnum", nullable = false)
    private Lab lab;

    public MainKey() {
    }

    public MainKey(User user, Lab lab) {
        this.user = user;
        this.lab = lab;
    }
}
