package com.batch.listener;

import java.time.LocalDateTime;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobListener implements JobExecutionListener{

	@Override
	public void beforeJob(JobExecution jobExecution) {
		System.out.println("Iniciou o Job " + jobExecution.getId() + " (" + jobExecution.getStatus().name() + ") - Data de execução -> "+LocalDateTime.now());		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		System.out.println("Finalizou o Job " + jobExecution.getId() + " (" + jobExecution.getStatus().name() + ") - Data de execução -> "+LocalDateTime.now());	
	}
}
