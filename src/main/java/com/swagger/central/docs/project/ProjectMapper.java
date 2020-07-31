package com.swagger.central.docs.project;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(unmappedTargetPolicy = ReportingPolicy.WARN, componentModel = "spring")
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO projectToProjectDto(Project project);

    List<ProjectDTO> projectsToProjectsDto(List<Project> project);

    @InheritInverseConfiguration
    Project projectDTOToProjectDomain(ProjectDTO project);

}