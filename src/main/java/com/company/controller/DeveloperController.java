package com.company.controller;


import com.company.entity.Developer;
import com.company.service.DeveloperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/developer")
public class DeveloperController {

    private static Logger log = LoggerFactory.getLogger(DeveloperController.class);

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/addDeveloper")
    public ResponseEntity<String> addDeveloper(@RequestBody Developer developer){
        System.err.println(developer);
        developerService.saveDeveloper(developer);
        log.debug("Request to add Developer : {}" +developer);
        return new ResponseEntity<>("Developer saved" , HttpStatus.CREATED);
    }


    @GetMapping("/getAllDeveloper")
    public ResponseEntity<List<Developer>> getAllDeveloper(){
        List<Developer> developerList = developerService.getAllDeveloper();
        log.debug("Request to get all developer list :{}"+developerList);
        return new ResponseEntity<>( developerList ,HttpStatus.OK);
    }


    @GetMapping("/getDeveloperById/{id}")
    public ResponseEntity<Developer> getDeveloperById( @PathVariable("id") int  id){
        Developer developer = developerService.getDeveloperById(id);
        log.debug("Request to get Developer by id : {}"+developer);
        return new ResponseEntity<>(developer,HttpStatus.OK);
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id){
        String msg = developerService.deleteDeveloperById(id);
        log.debug("Request to delete Developer by id : {}"+ msg);
        return  new ResponseEntity<>(msg , HttpStatus.OK);

    }

    @PutMapping("/updateDeveloper/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable("id") int id , @RequestBody Developer developer){
        Developer updateDeveloper = developerService.updateDeveloper(id ,developer);
        log.warn("Request to update Developer by id={}, newData={}"+developer);
        return new ResponseEntity<>(updateDeveloper , HttpStatus.OK);

    }


    @PostMapping("/addAllDevelopers")
    public ResponseEntity<String> addAllDevelopers(@RequestBody List<Developer> developerList) {
        developerService.addListOfDeveloper(developerList);
        log.info("Request to get all Developer list "+developerList);
        return new ResponseEntity<>("All developers saved successfully", HttpStatus.CREATED);
    }



   @GetMapping("/filter")
   public ResponseEntity<List<Developer>> filterdDeveloper(@RequestParam (required = false) String city,
                                                           @RequestParam(required = false) String gender){
       List<Developer> filterDeveloperList = new ArrayList<>();

        if(city != null){

            filterDeveloperList = developerService.filterDeveloperByCity(city);
            log.info("Filtering developers by city: {} ", city);
        }

        else if(gender != null){

            filterDeveloperList = developerService.filterDeveloperByGender(gender);
            log.info("Filtering developers by gender: {}", gender);
        }
        else if(gender != null &&  city != null){

           filterDeveloperList = developerService.filterDeveloperByGenderAndCity(gender,city);
            log.info("Filtering developers by city: {} and gender: {}", city, gender);
       }
        else{
             filterDeveloperList = developerService.getAllDeveloper();
            log.info("Fetching all developers (no filters applied)");
       }
       log.debug("Total developers found: {}", filterDeveloperList.size());
        return new ResponseEntity<>(filterDeveloperList ,HttpStatus.OK);


   }
}
