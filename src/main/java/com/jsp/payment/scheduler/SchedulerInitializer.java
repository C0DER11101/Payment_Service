package com.jsp.payment.scheduler;

import com.jsp.payment.service.TransactionService;
import jakarta.annotation.PostConstruct;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// helpful link: https://medium.com/@pvprasanth474/introduction-to-quartz-scheduler-in-java-7de47bbaa991

@Component
public class SchedulerInitializer {

    private String jobUniqueKey = "in progress";
    private String jobGroupName = "txStatus";

    @Autowired
    TransactionService txService;

    @PostConstruct
    public void init() {
        JobKey jobKey = new JobKey(jobUniqueKey, jobGroupName);

        // Define the Job
        JobDetail jobDetail = JobBuilder.newJob(UpdateScheduler.class)
                .withIdentity(jobKey)
                .build();

        // putting the instance of TransactionService so that we can retrieve it in UpdateScheduler
        jobDetail.getJobDataMap().put("timedTask", txService);

        // Define the Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity(jobUniqueKey, jobGroupName)
                .withSchedule(SimpleScheduleBuilder.repeatMinutelyForever(1))
                        //.withIntervalInMinutes(1)
                        .build();

        // Schedule the job
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }
}