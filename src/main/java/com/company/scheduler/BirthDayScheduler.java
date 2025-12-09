package com.company.scheduler;


import com.company.repository.DeveloperRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BirthDayScheduler {

    @Autowired
    private DeveloperRepository developerRepository;

    // ✅ Run Every day at 12am midnight
    @Scheduled(cron = "0 0 0 * * ?")
    @Transactional
    public void updateAgeOnBirthday() {
        developerRepository.updateAgeIfBirthday();
    }
}
