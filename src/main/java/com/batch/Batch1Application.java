package com.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({ "com.batch" })
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Batch1Application implements CommandLineRunner{

    @Autowired
    private ExecutorJob executorJob;
	
	public static void main(String[] args) {
		SpringApplication.run(Batch1Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		executorJob.execute();
		
	}

}
