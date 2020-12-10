package com.hjz.rabbitmq;

import java.io.Serializable;

public class MqMsgVo implements Serializable {
    private static final long serialVersionUID = -235290041230391448L;

    private int id;
    private String name;
    private long createTimestamp;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCreateTimestamp() {
        return createTimestamp;
    }

    public void setCreateTimestamp(long createTimestamp) {
        this.createTimestamp = createTimestamp;
    }

}
