package application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaInvestimentoRepository;
import domain.conta.ContaInvestimento;

public class CriarContaInvestimentoUseCase {
	
	private final ContaInvestimentoRepository repository;
	
	public CriarContaInvestimentoUseCase(ContaInvestimentoRepository repository) {
		this.repository = repository;
	}
	
	public ContaInvestimento novaContaInvestimento(String nomeBanco, String codigoBanco, BigDecimal saldoInicial) {
			
		ContaInvestimento contaInvestimento = new ContaInvestimento(
				null,
				LocalDateTime.now(),
				nomeBanco,
				codigoBanco,
				saldoInicial
				);
		
		ContaInvestimento contaInvestimentoCriada = repository.salvar(contaInvestimento);
		
		return contaInvestimentoCriada;
	}

}
