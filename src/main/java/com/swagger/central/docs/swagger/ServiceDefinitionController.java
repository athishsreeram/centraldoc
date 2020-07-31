package com.swagger.central.docs.swagger;


import com.swagger.central.docs.exception.ResourceNotFoundException;
import com.swagger.central.docs.project.ProjectDTO;
import com.swagger.central.docs.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.swagger.central.docs.swagger.ServiceDefinitionsContext;

/**
 * @author athish
 * <pre>
 *  Controller to serve the JSON from our in-memory store. So that UI can render the API-Documentation
 * </pre>
 */
@RestController
public class ServiceDefinitionController {

    @Autowired
    private ServiceDefinitionsContext definitionContext;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/service/{servicename}")
    public String getServiceDefinition(@PathVariable("servicename") String serviceName) {

        return definitionContext.getSwaggerDefinition(serviceName);

    }

    //Added
    @PostMapping("/service/{servicename}")
    public void putServiceDefinition(@PathVariable("servicename") String serviceName, @RequestBody String serviceDefinition) {

        definitionContext.addServiceDefinition(serviceName, serviceDefinition);
        try {
            ProjectDTO project = new ProjectDTO();
            project.setChannelsName(serviceName);
            project.setServiceDefinition(serviceDefinition);

            projectService.save(project);

        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
    }

    @PostMapping("/service/delete/{servicename}")
    public void deleteStudent(@PathVariable("servicename") String serviceName) throws ResourceNotFoundException {
        projectService.deleteById(serviceName);
        definitionContext.deleteSwaggerDefinition(serviceName);
    }

    @GetMapping("/ping")
    public String ping() {

        return "Hello";

    }

}
