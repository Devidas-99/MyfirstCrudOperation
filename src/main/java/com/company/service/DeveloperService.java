package com.company.service;

import com.company.entity.Developer;

import java.util.List;

public interface DeveloperService {

   String saveDeveloper(Developer developer);

   List<Developer> getAllDeveloper();

   Developer getDeveloperById(int id);

   String deleteDeveloperById(int id);

   Developer updateDeveloper(int id , Developer newData);


  void addListOfDeveloper(List<Developer> developerList);

   List<Developer> filterDeveloperByCity(String city);

   List<Developer> filterDeveloperByGender(String gender);

   List<Developer> filterDeveloperByGenderAndCity(String gender , String city);


}
