package com.YB.OMSsystem;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
@ComponentScan({"com.YB.OMSsystem"})
public class OMSsystemApplication {
 
    public static void main(String[] args) { 
        SpringApplication.run(OMSsystemApplication.class, args);
    }
 
}
