package com.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.batch.csv.CSVtaxReader;
import com.batch.entities.Tax;
import com.batch.listener.JobListener;
import com.batch.vo.TaxVO;
import com.batch.writer.MongoTaxWriter;

@Configuration
@EnableBatchProcessing
public class TaxJob {
	
	   @Bean
	    public Job jobTax(JobBuilderFactory jobBuilderFactory, JobListener jobListener, 
	    		StepBuilderFactory stepBuilderFactory, CSVtaxReader csvTaxReader, MongoTaxWriter taxWriter,
	    		ItemProcessor< TaxVO, Tax> taxVOtoTaxConverterProcessor) {
	        return jobBuilderFactory
	                .get("jobTax")
	                .listener(jobListener)
	                .incrementer(new RunIdIncrementer())
	                .start(step1(stepBuilderFactory,csvTaxReader,taxWriter,taxVOtoTaxConverterProcessor))
	                .build();
	    }
	 
	    @Bean
	    public Step step1(StepBuilderFactory stepBuilderFactory, CSVtaxReader csvTaxReader, MongoTaxWriter taxWriter,
	    		ItemProcessor< TaxVO, Tax> taxVOtoTaxConverterProcessor	) {
	        return stepBuilderFactory
	                .get("step1")
	                .<TaxVO,Tax>chunk(10)
	                .reader(csvTaxReader)
	                .processor(taxVOtoTaxConverterProcessor)
	                .writer(taxWriter)
	                .build();
	    }
	

}
