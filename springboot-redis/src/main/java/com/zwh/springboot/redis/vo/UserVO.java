package com.zwh.springboot.redis.vo;

/**
 * @author zhuwh
 * @date 2018/7/27 16:40
 * @desc
 */
public class UserVO {

    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
