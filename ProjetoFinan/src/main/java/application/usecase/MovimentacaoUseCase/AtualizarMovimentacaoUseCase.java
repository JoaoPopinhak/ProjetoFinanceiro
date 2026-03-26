package application.usecase.MovimentacaoUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.service.MovimentacaoService;
import domain.conta.Conta;
import domain.movimentacao.Movimentacao;
import domain.movimentacao.TipoMovimentacao;
import infrastructure.persistence.JpaContaRepository;
import infrastructure.persistence.JpaMovimentacaoRepository;

public class AtualizarMovimentacaoUseCase {
	
	
	JpaContaRepository contaRepository;
	JpaMovimentacaoRepository movimentacaoRepository;
	
	public AtualizarMovimentacaoUseCase(JpaContaRepository contaRepository,JpaMovimentacaoRepository movimentacaoRepository) {
		this.contaRepository = contaRepository;
		this.movimentacaoRepository = movimentacaoRepository;
	}
	
	public void atualizarMovimentacao(Long idMovimentacao, 
			                          TipoMovimentacao tipoMovimentacao, 
			                          BigDecimal ValorMovimentacao,
			                          LocalDateTime DataHoraMovimentacao,
			                          Long idContaOrigem,
			                          Long idContaDestino,
			                          String descricao) {
		
		Movimentacao movimentacao = movimentacaoRepository.buscarPorId(idMovimentacao).orElseThrow(() -> new RuntimeException("Movimentacao não encontrada"));
		
		Conta contaOrigem = contaRepository.BuscarPorId(movimentacao.getIdContaOrigem()).orElseThrow();
		Conta contaDestino = movimentacao.getIdContaDestino() == null ? null : contaRepository.BuscarPorId(movimentacao.getIdContaDestino()).orElseThrow();
		
		if(idContaOrigem != null) {
			contaOrigem = contaRepository.BuscarPorId(idContaOrigem).orElseThrow(() -> new RuntimeException("Conta de origem não localizada"));
		}
		
		if(idContaDestino != null) {
			contaDestino = contaRepository.BuscarPorId(idContaDestino).orElseThrow(() -> new RuntimeException("Conta de destino não localizada"));
		}
		
		MovimentacaoService.estornarMovimentacao(contaOrigem, contaDestino, movimentacao);
		
		movimentacao.atualizarMovimentacao(tipoMovimentacao, ValorMovimentacao, DataHoraMovimentacao, idContaOrigem, idContaDestino, descricao);
		
		MovimentacaoService.aplicarMovimentacao(contaOrigem, contaDestino, movimentacao);
		
		movimentacaoRepository.salvar(movimentacao);
		
		contaRepository.salvar(contaOrigem);
		
		if(contaDestino != null) {
			contaRepository.salvar(contaDestino);
		}
	}
}
