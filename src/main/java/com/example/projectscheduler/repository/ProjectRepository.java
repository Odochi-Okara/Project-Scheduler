package com.example.projectscheduler.repository;


import com.example.projectscheduler.model.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    Iterable<Project> findAllById(Iterable<Long> iterable);

    Project findByProjectIdentifier(String projectIdentifier);

    @Override
    Iterable<Project> findAll();
}
