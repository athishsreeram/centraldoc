package com.springcloud.example.employee;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableAutoConfiguration
@RestController
@EnableConfigurationProperties
@SpringBootApplication

public class DocApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    public static void main(String[] args) {
        SpringApplication.run(DocApplication.class, args);

    }


    @RequestMapping("/svc")
    public List<String> svc() {
        return discoveryClient.getServices();
    }

    @RequestMapping("/instances")
    public List<ServiceInstance> instances() {
        List<ServiceInstance> si = new ArrayList<>();
        discoveryClient.getServices().forEach(
                svc->  si.addAll(discoveryClient.getInstances(svc)));
        return si;
    }
}

