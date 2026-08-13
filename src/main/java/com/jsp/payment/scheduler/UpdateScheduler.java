package com.jsp.payment.scheduler;

import com.jsp.payment.service.TransactionService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.quartz.JobBuilder.newJob;

public class UpdateScheduler implements Job {

    @Override
    public void execute(JobExecutionContext jec) { // JobExecutionContext provides access to JobDetail, Trigger, Scheduler and MergedJobDataMap (a combination of job and trigger data)
        TransactionService txService = (TransactionService)jec.getJobDetail().getJobDataMap().get("timedTask");
        txService.processFindByTxStatus("in progress");
    }
}