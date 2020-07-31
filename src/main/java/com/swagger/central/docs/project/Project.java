package com.swagger.central.docs.project;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.util.Date;

@Entity
@Data
public class Project {

    @Id
    private String channelsName;

    @Size(min = 500, max = 3000000)
    private String serviceDefinition;

    @CreationTimestamp
    private Date created_date;


}