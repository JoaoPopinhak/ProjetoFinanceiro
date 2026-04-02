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
		if (idContaOrigem != null) {
			if (contaDestinoAtual != null && idContaOrigem.equals(contaDestinoAtual.getId())) {
				
				contaOrigemNova = contaDestinoAtual;	
			}else if(!idContaOrigem.equals(contaOrigemAtual.getId())){
				
				contaOrigemNova = contaRepository.BuscarPorId(idContaOrigem).orElseThrow(() -> new RuntimeException("Conta origem não encontrada"));
			}
		}
		
		Conta contaDestinoNova = contaDestinoAtual;
		if (idContaDestino != null) {
			if (idContaDestino.equals(contaOrigemAtual.getId())) {
				
				contaDestinoNova = contaOrigemAtual;	
			}else if(contaDestinoAtual == null || !idContaDestino.equals(contaDestinoAtual.getId())) {
				
				contaDestinoNova = contaRepository.BuscarPorId(idContaDestino).orElseThrow(() -> new RuntimeException("Conta destino não encontrada"));	
			}
		}
		
		movimentacao.atualizarMovimentacao(tipoMovimentacao, ValorMovimentacao, DataHoraMovimentacao, idContaOrigem, idContaDestino, descricao);
		
		if(movimentacao.getTipoMovimentacao() != TipoMovimentacao.TRANSFERENCIA) {
				contaDestinoNova = null;
		}
		
		MovimentacaoService.aplicarMovimentacao(contaOrigemNova, contaDestinoNova, movimentacao);
		
		//REALIZA A PERSISTENCIA
        contaRepository.salvar(contaOrigemAtual);

        if (contaDestinoAtual != null) {
            contaRepository.salvar(contaDestinoAtual);
        }

        if (!contaOrigemAtual.getId().equals(contaOrigemNova.getId())) {
            contaRepository.salvar(contaOrigemNova);
        }

        if (contaDestinoNova != null && (contaDestinoAtual == null || !contaDestinoAtual.getId().equals(contaDestinoNova.getId()))) {

            contaRepository.salvar(contaDestinoNova);
        }

        movimentacaoRepository.salvar(movimentacao);
	}
}
