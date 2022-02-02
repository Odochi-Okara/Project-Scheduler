package com.example.projectscheduler.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "project name is required")
    private String  projectName;

    @NotBlank(message = "project Identifier needed")
    @Size(max = 5, min = 4, message = "Please use 5 to 6 characters")
    @Column(updatable = false, unique = true)
    private String projectIdentifier;

    @NotBlank(message = "Please enter a message")
    private String description;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date start_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date end_date;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date created_At;

    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date updated_AT;

    @PrePersist
    protected  void onCreate(){
        this.created_At = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        this.updated_AT = new Date();
    }
}
