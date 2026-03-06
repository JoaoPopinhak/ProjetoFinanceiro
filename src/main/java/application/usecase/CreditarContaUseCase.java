package application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaRepository;
import application.repository.MovimentacaoRepository;
import domain.conta.Conta;
import domain.movimentacao.Movimentacao;
import domain.movimentacao.TipoMovimentacao;

public class CreditarContaUseCase {
	
	private ContaRepository contaRepository;
	private MovimentacaoRepository movimentacaoRepository;
	
	public CreditarContaUseCase(ContaRepository contaRepository, MovimentacaoRepository movimentacaoRepository) {
		this.contaRepository = contaRepository;
		this.movimentacaoRepository = movimentacaoRepository;
	}
	
	public void creditar(Long idConta, BigDecimal valorCredito, String descricao) {
		
		Conta conta = contaRepository.BuscarPorId(idConta).orElseThrow(() -> new RuntimeException("Conta não localizada"));
		
		conta.creditar(valorCredito);
		
		Movimentacao movimentacao = new Movimentacao(
				null,
				TipoMovimentacao.RECEITA,
				valorCredito,
				LocalDateTime.now(),
				conta.getId(),
				descricao);
		
		
		movimentacaoRepository.salvar(movimentacao);
		
		contaRepository.salvar(conta);

	}

}
