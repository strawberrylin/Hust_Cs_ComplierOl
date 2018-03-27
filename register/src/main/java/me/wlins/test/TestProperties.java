package me.wlins.test;
/*
 * @Author: Strawberrylin
 * @Description:
 * @Date: Created in 上午8:58 18-3-25
 * @Modified By:
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
public class TestProperties {
    private String name;
    private int size;

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
