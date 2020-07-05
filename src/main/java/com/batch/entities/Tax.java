package com.batch.entities;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Document(collection = "taxs")
@Data
@Builder
public class Tax {

	@Id
	private String id;
	private String taxValue;
	private String taxType;
	private LocalDate dueDate;
	private String name;
	private Long consumptionValue;
		
}
