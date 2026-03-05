package application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaBancariaRepository;
import domain.conta.ContaBancaria;

public class CriarContaBancariaUseCase {
	
	
	private final ContaBancariaRepository repository;
	
	public CriarContaBancariaUseCase(ContaBancariaRepository repository) {
		this.repository = repository;
	}
	
	public ContaBancaria novaContaBancaria(String nomeBanco, String codigoBanco, BigDecimal saldoInicial) {
		
		ContaBancaria contaBancaria = new ContaBancaria(
				null,
				LocalDateTime.now(),
				nomeBanco,
				codigoBanco, 
				saldoInicial
				);
		
		ContaBancaria contaBancariaCriada = repository.salvar(contaBancaria);
		
		return contaBancariaCriada;
	}
	
}
