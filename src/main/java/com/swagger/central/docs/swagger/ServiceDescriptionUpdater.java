package com.swagger.central.docs.swagger;

import com.swagger.central.docs.project.ProjectDTO;
import com.swagger.central.docs.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceDescriptionUpdater {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private ServiceDefinitionsContext definitionContext;

    @Scheduled(fixedDelayString = "${swagger.config.refreshrate}")
    public void refreshSwaggerConfigurations() {

        System.out.println("called +++++");
        List<ProjectDTO> serLst = projectService.findAll();
        for (int i = 0; i <= serLst.size()-1; i++) {
            definitionContext.addServiceDefinition(serLst.get(i).getChannelsName(), serLst.get(i).getServiceDefinition());
        }
    }

}