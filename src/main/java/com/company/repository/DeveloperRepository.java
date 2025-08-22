package com.company.repository;

import com.company.entity.Developer;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface DeveloperRepository extends JpaRepository<Developer , Integer>{



    @Modifying
    @Query("UPDATE Developer d " +
            "SET d.age = d.age + 1 " +
            "WHERE FUNCTION('day', d.dob) = FUNCTION('day', CURRENT_DATE) " +
            "AND FUNCTION('month', d.dob) = FUNCTION('month', CURRENT_DATE)")

    void updateAgeIfBirthday();


}


