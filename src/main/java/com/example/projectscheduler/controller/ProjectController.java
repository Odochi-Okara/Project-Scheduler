package com.example.projectscheduler.controller;



import com.example.projectscheduler.model.Project;
import com.example.projectscheduler.services.MapValidationErrorService;
import com.example.projectscheduler.services.ProjectServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    private final ProjectServices projectServices;

    private final MapValidationErrorService mapValidationErrorService;

    @Autowired
    public ProjectController(ProjectServices projectServices, MapValidationErrorService mapValidationErrorService) {
        this.projectServices = projectServices;
        this.mapValidationErrorService = mapValidationErrorService;
    }

    @PostMapping("")

    public ResponseEntity<?> createAndUpdateProject(@RequestBody @Valid Project project, BindingResult result){


        ResponseEntity<?> errorMap = mapValidationErrorService.mapValidationService(result);
        if(result.hasErrors()) {return errorMap;};

//        if(result.hasErrors()){
//            Map<String, String> mapError = new HashMap<>();
//
//            for(FieldError error: result.getFieldErrors()){
//                mapError.put(error.getField(), error.getDefaultMessage());
//            }
//            return new ResponseEntity<Map<String, String>>(mapError, HttpStatus.BAD_REQUEST);
//        }
        var project1 = projectServices.createOrUpdateProject(project);
        return new ResponseEntity<Project>(project1, HttpStatus.CREATED);
    }

    @GetMapping("/{projectId}")
    public ResponseEntity<?> findProject(@PathVariable String projectId){
        var project = projectServices.findProjectByIdentifier(projectId);
        return new ResponseEntity<Project>(project, HttpStatus.OK);
    }

    @GetMapping("/list_of_projects")
    public Iterable<Project> findAll(){
        Iterable<Project> allProjects = projectServices.findAll();
        return allProjects;
    }

    @DeleteMapping("/delete_project/{projectIdentifier}")
    public ResponseEntity<?> deleteProject(@PathVariable String projectIdentifier){
        projectServices.deleteProject(projectIdentifier);

        return new ResponseEntity<String>("Project with identifier:   '"+projectIdentifier+"' deleted",HttpStatus.OK);
    }

//    @PutMapping("/update/{projectIdentifier}")
//    public ResponseEntity<?> updateProject(@PathVariable String projectIdentifier, Project project){
//        projectServices.updateProject(projectIdentifier,project);
//
//    }
}
