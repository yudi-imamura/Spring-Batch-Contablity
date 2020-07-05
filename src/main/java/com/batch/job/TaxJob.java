package com.batch.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Autowired
	private JobListener jobListener;
	
	@Autowired
	private CSVtaxReader csvTaxReader;

	@Autowired
	private MongoTaxWriter taxWriter;
	
	@Autowired
	@Qualifier(value="taxVOtoTaxConverterProcessor")
	private ItemProcessor< TaxVO, Tax> taxVOtoTaxConverterProcessor;

	   @Bean
	    public Job jobTax() {
	        return jobBuilderFactory
	                .get("jobTax")
	                .listener(jobListener)
	                .incrementer(new RunIdIncrementer())
	                .start(step1())
	                .build();
	    }
	 
	    @Bean
	    public Step step1() {
	        return stepBuilderFactory
	                .get("step1")
	                .listener(null)
	                .<TaxVO,Tax>chunk(3)
	                .reader(csvTaxReader)
	                .processor(taxVOtoTaxConverterProcessor)
	                .writer(taxWriter)
	                .build();
	    }
	

}
