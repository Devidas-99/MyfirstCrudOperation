package com.company.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;


@Entity
@Setter
@Getter
@ToString

public class Developer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fName;

    private String lName;

    private int age;

    private String city;

    private String gender;

    private long salary;

    private int yearOfBirth;

    private String developerId;

    private LocalDateTime dob;

}
