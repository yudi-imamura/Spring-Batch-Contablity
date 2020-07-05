package com.batch.csv;

import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.batch.vo.TaxVO;

@Component
public class CSVtaxReader extends FlatFileItemReader<TaxVO> {

	public CSVtaxReader() {
		setResource(new ClassPathResource("taxs.csv"));
		setLinesToSkip(1);
		setLineMapper(lineMapper());
	}

	@Bean
	public LineMapper<TaxVO> lineMapper() {
		DefaultLineMapper<TaxVO> lineMapper = new DefaultLineMapper<>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setDelimiter(";");
		lineTokenizer.setNames(columnNames());
		BeanWrapperFieldSetMapper<TaxVO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
		fieldSetMapper.setTargetType(TaxVO.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	private String[] columnNames() {
		return new String[] { "tipoConta", "dataVencimento", "valor","valorConsumo","nomeCliente"};
	}
	
}
