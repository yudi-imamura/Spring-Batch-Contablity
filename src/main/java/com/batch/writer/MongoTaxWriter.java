package com.batch.writer;

import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.batch.entities.Tax;

@Component
public class MongoTaxWriter extends MongoItemWriter<Tax> {

	public MongoTaxWriter(MongoTemplate mongoTemplate) {
		setTemplate(mongoTemplate);
		setCollection("taxs");

	}
}
