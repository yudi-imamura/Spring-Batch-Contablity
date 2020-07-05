package com.batch.vo;

import java.time.LocalDate;

import lombok.Data;

@Data
public class TaxVO {

	private LocalDate dataVencimento;
	private String valor;
	private String tipoConta;
	private Long valorConsumo;
	private String nomeCliente;
}
