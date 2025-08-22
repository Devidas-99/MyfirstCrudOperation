package com.company.helper;

import com.company.entity.Developer;

public class DeveloperId {

    public static String DeveloperID(Developer developer){
      String fName =  developer.getFName();
      String lName =    developer.getLName();
      int YOB =  developer.getYearOfBirth();

       char  a = fName.charAt(0);

        int lastTwo = YOB % 100;
        String b = String.format("%02d", lastTwo);

       String developerID = a + lName + b;

      return developerID;


    }
}
