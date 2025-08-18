package com.company.helper;

import com.company.entity.Developer;

public class DeveloperId {

    public static String DeveloperID(Developer developer){
      String fristname =  developer.getFName();
      String lastname =    developer.getLName();
      int YOB =  developer.getYearOfBirth();

       char  a = fristname.charAt(0);

        int lastTwo = YOB % 100;
        String b = String.format("%02d", lastTwo);


       String developerID = a + lastname + b;

      return developerID;


    }
}
