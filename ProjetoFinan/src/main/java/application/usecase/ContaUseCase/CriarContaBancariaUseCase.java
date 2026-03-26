package application.usecase.ContaUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaRepository;
import domain.conta.ContaBancaria;

public class CriarContaBancariaUseCase {
	
	
	private final ContaRepository repository;
	
	public CriarContaBancariaUseCase(ContaRepository repositoryConta) {
		this.repository = repositoryConta;
	}
	
	public ContaBancaria novaContaBancaria(String nomeBanco, String codigoBanco, BigDecimal saldoInicial) {
		
		ContaBancaria contaBancaria = new ContaBancaria(
				null,
				LocalDateTime.now(),
				nomeBanco,
				codigoBanco, 
				saldoInicial
				);
		
		ContaBancaria contaBancariaCriada = (ContaBancaria) repository.salvar(contaBancaria);
		
		return contaBancariaCriada;
	}
	
}
