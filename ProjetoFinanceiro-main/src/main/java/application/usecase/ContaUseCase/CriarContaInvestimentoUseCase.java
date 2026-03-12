package application.usecase.ContaUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaRepository;
import domain.conta.ContaInvestimento;

public class CriarContaInvestimentoUseCase {
	
	private final ContaRepository repository;
	
	public CriarContaInvestimentoUseCase(ContaRepository repository) {
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
		
		ContaInvestimento contaInvestimentoCriada = (ContaInvestimento) repository.salvar(contaInvestimento);
		
		return contaInvestimentoCriada;
	}

}
