package com.lyoyang.lab.monitor.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class LabMonitorAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(LabMonitorAdminApplication.class, args);
    }

}
