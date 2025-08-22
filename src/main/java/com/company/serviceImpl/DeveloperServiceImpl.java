package com.company.serviceImpl;

import com.company.entity.Developer;
import com.company.helper.DeveloperId;
import com.company.repository.DeveloperRepository;
import com.company.service.DeveloperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeveloperServiceImpl implements DeveloperService {

private static final Logger log = LoggerFactory.getLogger(DeveloperServiceImpl.class);


    @Autowired
    private DeveloperRepository developerRepository;



    @Override
    public String saveDeveloper (Developer developer){
        log.info("save developer successfully" + developer);
        // ✅ Calculate Age from DOB
        if (developer.getDob() != null) {
            int calculatedAge =java.time.Period.between(developer.getDob().toLocalDate(),java.time.LocalDate.now()).getYears();
            developer.setAge(calculatedAge);
        }
        String developerID = DeveloperId.DeveloperID(developer);
        developer.setDeveloperId(developerID);
        Developer savedDeveloper = developerRepository.save(developer);
        return "developer saved";
    }

    @Override
    public List<Developer> getAllDeveloper() {
        List<Developer> developerList = developerRepository.findAll();
        log.info("featch all developer , count{}" +developerList.size());
        return developerList;
    }

    @Override
    public Developer getDeveloperById(int id) {
        Developer developer =  developerRepository.findById(id).orElseThrow(() -> new NullPointerException("Developer with id not found " +id));
        log.info("Student featch by id" +developer);
        return developer;

    }

    @Override
    public String deleteDeveloperById(int id) {
        developerRepository.deleteById(id);

        return "Developer deleted";
    }

    @Override
    public Developer updateDeveloper(int id, Developer newData) {
        Developer developer = developerRepository.findById(id).orElseThrow(()-> new NullPointerException("Developer is not found with this id" +id));
        log.error("Developer found with this id" +developer);
        System.err.println("old developer from db" +developer);
        System.err.println("Developer object with values to be updated"+newData);
        developer.setFName(newData.getFName());
        developer.setLName(newData.getLName());
        developer.setAge(newData.getAge());
        developer.setCity(newData.getCity());
        developer.setSalary(newData.getSalary());
        Developer updatedDeveloper = developerRepository.save(developer);
        log.info("Updated developer" +updatedDeveloper);
        System.err.println("Developer with updated value "+updatedDeveloper);
        return updatedDeveloper ;
    }

    @Override
    public void addListOfDeveloper(List<Developer> developerList) {
        developerRepository.saveAll(developerList);
    }


    @Override
    public List<Developer> filterDeveloperByCity(String city) {
        List<Developer> developerList = developerRepository.findAll();
      List<Developer> filterdList=  developerList.stream().filter(developer -> developer.getCity().equalsIgnoreCase(city)).collect(Collectors.toList());
        return filterdList;
    }

    @Override
    public List<Developer> filterDeveloperByGender(String gender) {
        List<Developer> developerList = developerRepository.findAll();
        List<Developer> filterdList=  developerList.stream().filter(developer -> developer.getGender().equalsIgnoreCase(gender)).collect(Collectors.toList());
        return filterdList;


    }


    @Override
    public List<Developer> filterDeveloperByGenderAndCity(String gender, String city) {
        List<Developer> allDevelopers = developerRepository.findAll();

        List<Developer> developerList = allDevelopers.stream()
                .filter(developer -> developer.getCity().equalsIgnoreCase(city))
                .filter(developer -> developer.getGender().equalsIgnoreCase(gender))
                .collect(Collectors.toList());

        return developerList;

    }




}
