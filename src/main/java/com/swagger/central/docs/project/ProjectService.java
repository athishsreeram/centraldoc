package com.swagger.central.docs.project;

import com.swagger.central.docs.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {


    @Autowired
    private ProjectRespository projectRespository;

    @Autowired
    private ProjectMapper projectMapper;


    public List<ProjectDTO> findAll() {
        List<Project> lstPjt = projectRespository.findAll();

        if (!lstPjt.isEmpty()) {
            return projectMapper.projectsToProjectsDto(lstPjt);
        }

        return new ArrayList<ProjectDTO>();

    }

    public Optional<ProjectDTO> findById(String id) {

        Optional<Project> pjt = projectRespository.findById(id);

        if (pjt.isPresent()) {
            return Optional.of(projectMapper.projectToProjectDto(pjt.get()));
        }

        return Optional.empty();
    }

    public ProjectDTO save(ProjectDTO project) throws ResourceNotFoundException {

        Project pjt = projectMapper.projectDTOToProjectDomain(project);


        pjt = projectRespository.save(pjt);


        return projectMapper.projectToProjectDto(pjt);

    }

    public void deleteById(String id) throws ResourceNotFoundException {

        Optional<Project> pjtPr = projectRespository.findById(id);

        if (pjtPr.isPresent()) {
            projectRespository.delete(pjtPr.get());
        } else {
            throw new ResourceNotFoundException(" Collection id not found");
        }


    }
}