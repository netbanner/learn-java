package com.example.config;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import lombok.Data;

import java.util.List;

@Data
@XStreamAlias("Server")
public class ServerCfg {

    @XStreamAsAttribute
    private int port = 8005;

    @XStreamAsAttribute
    private String shutDown = "SHUTDOWN";

    private List<ServiceCfg> services;

    public List<ServiceCfg> getServices() {
        return services;
    }

    public void setServices(List<ServiceCfg> services) {
        this.services = services;
    }
}
