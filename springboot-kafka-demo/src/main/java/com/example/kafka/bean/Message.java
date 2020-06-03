package com.example.kafka.bean;

import java.util.Date;

/**
 * @author zhuwh
 * @date 2018/11/26 15:29
 * @desc
 */
public class Message {

    private Long id;

    private String msg;

    private Date time;

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", time=" + time +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
