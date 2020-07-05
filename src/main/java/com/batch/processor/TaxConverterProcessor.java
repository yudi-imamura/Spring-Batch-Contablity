package com.batch.processor;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.batch.entities.Tax;
import com.batch.vo.TaxVO;

@Component(value="taxVOtoTaxConverterProcessor")
public class TaxConverterProcessor implements ItemProcessor<TaxVO,Tax>{

	@Override
	public Tax process(TaxVO item) throws Exception {
		return Tax.builder().name(StringUtils.defaultString(item.getNomeCliente()))
					.taxType(StringUtils.defaultString(item.getTipoConta(), "INDEFINIDO"))
					.consumptionValue(item.getValorConsumo())
					.taxValue(StringUtils.defaultString(item.getValor(),"0,00"))
					.dueDate(item.getDataVencimento()).build();
	}
}
