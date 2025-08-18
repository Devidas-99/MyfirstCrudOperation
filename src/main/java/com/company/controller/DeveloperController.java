package com.company.controller;


import com.company.entity.Developer;
import com.company.service.DeveloperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("developer")
public class DeveloperController {

    @Autowired
    private DeveloperService developerService;

    @PostMapping("/add")
    public ResponseEntity<String> addDeveloper(@RequestBody Developer developer){
        System.err.println(developer);
        developerService.saveDeveloper(developer);
        return new ResponseEntity<>("Developer saved" , HttpStatus.CREATED);
    }


    @GetMapping("/getAllDeveloper")
    public ResponseEntity<List<Developer>> getAllDeveloper(){
        List<Developer> developerList = developerService.getAllDeveloper();
        return new ResponseEntity<>( developerList ,HttpStatus.OK);
    }


    @GetMapping("/getDeveloperById/{id}")
    public ResponseEntity<Developer> getDeveloperById( @PathVariable("id") int  id){
        Developer developer = developerService.getDeveloperById(id);
        return new ResponseEntity<>(developer,HttpStatus.OK);
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") int id){
        String msg = developerService.deleteDeveloperById(id);
        return  new ResponseEntity<>(msg , HttpStatus.OK);

    }

    @PutMapping("/updateDeveloper/{id}")
    public ResponseEntity<Developer> updateDeveloper(@PathVariable("id") int id , @RequestBody Developer developer){
        Developer updateDeveloper = developerService.updateDeveloper(id ,developer);
        return new ResponseEntity<>(updateDeveloper , HttpStatus.OK);

    }


    @PostMapping("/addAllDevelopers")
    public ResponseEntity<String> addAllDevelopers(@RequestBody List<Developer> developerList) {
        developerService.addListOfDeveloper(developerList);
        return new ResponseEntity<>("All developers saved successfully", HttpStatus.CREATED);
    }



   @GetMapping("/filter")
   public ResponseEntity<List<Developer>> filterdDeveloper(@RequestParam (required = false) String city,
                                                           @RequestParam(required = false) String gender){
       List<Developer> filterDeveloperLsit = new ArrayList<>();

        if(city != null){

            filterDeveloperLsit = developerService.filterDeveloperByCity(city);
        }

        else if(gender != null){

            filterDeveloperLsit = developerService.filterDeveloperByGender(gender);
        }
        else if(gender != null &&  city != null){

           filterDeveloperLsit = developerService.filterDeveloperByGenderAndCity(gender,city);
       }
        else{
             filterDeveloperLsit = developerService.getAllDeveloper();
       }

        return new ResponseEntity<>(filterDeveloperLsit ,HttpStatus.OK);

   }
}
