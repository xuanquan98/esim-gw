package com.quanvx.esim.job;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class JobSchedule {
    // Run every 3 minutes
    @Scheduled(cron = "0 */3 * * * *") // Every 3 minutes
    public void runJob() {
        System.out.println("Job is running every 3 minutes: " + java.time.LocalDateTime.now());
        // Add your job logic here
    }
}
