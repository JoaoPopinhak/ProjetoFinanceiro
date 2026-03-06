package application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaRepository;
import application.repository.MovimentacaoRepository;
import domain.conta.Conta;
import domain.movimentacao.Movimentacao;
import domain.movimentacao.TipoMovimentacao;

public class DebitarContaUseCase {
	
	private ContaRepository contaRepository;
	private MovimentacaoRepository movimentacaoRepository;
	
	public DebitarContaUseCase (ContaRepository contaRepository, MovimentacaoRepository movimentacaoRepository) {
		this.contaRepository = contaRepository;
		this.movimentacaoRepository = movimentacaoRepository;
	}
	
	public void sacar(Long idConta, BigDecimal valorDespesa, String descricao) {
		
		Conta conta = contaRepository.BuscarPorId(idConta).orElseThrow(() -> new RuntimeException("Conta não localizada."));
		
		conta.debitar(valorDespesa);
		
		Movimentacao movimentacao = new Movimentacao(
				null,
				TipoMovimentacao.DESPESA,
				valorDespesa,
				LocalDateTime.now(),
				conta.getId(),
				descricao);
		
		movimentacaoRepository.salvar(movimentacao);
		
		contaRepository.salvar(conta);	
	}
}
