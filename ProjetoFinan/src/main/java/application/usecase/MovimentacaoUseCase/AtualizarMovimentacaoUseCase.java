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
		
		Conta contaOrigemAtual = contaRepository.BuscarPorId(movimentacao.getIdContaOrigem()).orElseThrow();
		Conta contaDestinoAtual = movimentacao.getIdContaDestino() == null ? null : contaRepository.BuscarPorId(movimentacao.getIdContaDestino()).orElseThrow();
		
		
		MovimentacaoService.estornarMovimentacao(contaOrigemAtual, contaDestinoAtual, movimentacao);
		
		Conta contaOrigemNova = contaOrigemAtual;
		Conta contaDestinoNova = contaDestinoAtual == null ? null : contaDestinoAtual;
		
		
		if(idContaOrigem != null) {
			contaOrigemNova = contaRepository.BuscarPorId(idContaOrigem).orElseThrow(() -> new RuntimeException("Conta de origem não localizada"));
		}
		
		if(idContaDestino != null) {
			contaDestinoNova = contaRepository.BuscarPorId(idContaDestino).orElseThrow(() -> new RuntimeException("Conta de destino não localizada"));
		}
		
		movimentacao.atualizarMovimentacao(tipoMovimentacao, ValorMovimentacao, DataHoraMovimentacao, idContaOrigem, idContaDestino, descricao);
		
		MovimentacaoService.aplicarMovimentacao(contaOrigemNova, contaDestinoNova, movimentacao);
		
		//SALVANDO NO BANCO CONTA ATUAL
		contaRepository.salvar(contaOrigemAtual);
		
		if(contaDestinoAtual != null) {
			contaRepository.salvar(contaDestinoAtual);
		}
		
		//SALVANDO NO BANCO CONTA NOVA SE TER SIDO MODIFICADO CONTA ATUAL
		if(!contaOrigemAtual.getId().equals(contaOrigemNova.getId())) {
			contaRepository.salvar(contaOrigemNova);
		}
		
		if(contaDestinoNova != null) {
			if(contaDestinoAtual == null || !contaDestinoAtual.getId().equals(contaDestinoNova.getId())) {
				contaRepository.salvar(contaDestinoNova);
			}
		}
		
		movimentacaoRepository.salvar(movimentacao);
	}
}
