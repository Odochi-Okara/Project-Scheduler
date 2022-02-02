package com.example.projectscheduler.services;


import com.example.projectscheduler.exceptions.ProjectIdException;
import com.example.projectscheduler.model.Project;
import com.example.projectscheduler.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServices {

    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectServices(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Project createOrUpdateProject(Project project){


       try{
           project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
           return projectRepository.save(project);
       } catch (Exception e){
           throw new ProjectIdException("Project ID: " + project.getProjectIdentifier().toUpperCase());
       }
    }

    public Project findProjectByIdentifier(String projectIdentifier){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);
            if(project== null){
                throw new ProjectIdException("Project: " +projectIdentifier+ " does not exist");
            }
            return project;}

    public Iterable<Project> findAll(){
        return projectRepository.findAll();
    }

    public void deleteProject(String projectIdentifier) {
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier);

        if(project==null){
            throw new ProjectIdException("Project does not exist");
        }
        projectRepository.delete(project);

    }

    public Project updateProject(String projectIdentifier, Project project){
        Project project1 = projectRepository.findByProjectIdentifier(projectIdentifier);

        if(project1==null){
            throw new ProjectIdException("Project does not exist");
        }
        project1.setProjectName(project.getProjectName());
        project1.setProjectIdentifier(project.getProjectIdentifier());
        project1.setDescription(project.getDescription());
        project1.setCreated_At(project.getCreated_At());
        project1.setUpdated_AT(project.getUpdated_AT());
          project1.setStart_date(project.getStart_date());
        project1.setEnd_date(project.getEnd_date());
        return projectRepository.save(project1);

    }
}