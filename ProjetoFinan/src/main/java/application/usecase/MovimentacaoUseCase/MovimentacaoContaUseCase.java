package application.usecase.MovimentacaoUseCase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaRepository;
import application.repository.MovimentacaoRepository;
import application.service.MovimentacaoService;
import domain.conta.Conta;
import domain.movimentacao.Movimentacao;
import domain.movimentacao.TipoMovimentacao;

public class MovimentacaoContaUseCase {
	
	private ContaRepository contaRepository;
	private MovimentacaoRepository movimentacaoRepository;
	
	public MovimentacaoContaUseCase (ContaRepository contaRepository, MovimentacaoRepository movimentacaoRepository) {
		this.contaRepository = contaRepository;
		this.movimentacaoRepository = movimentacaoRepository;
	}
	
	public void novaMovimentacao(TipoMovimentacao tipoMovimentacao, 
			            BigDecimal valorMovimentacao, 
			            LocalDateTime dataHoraMovimentacao, 
			            Long idContaOrigem,
			            Long idContaDestino,
			            String descricao) {
		
		Conta contaOrigem = contaRepository.BuscarPorId(idContaOrigem).orElseThrow(() -> new RuntimeException("Conta de origem não localizada"));
		
		Conta contaDestino = idContaDestino == null ? null : contaRepository.BuscarPorId(idContaDestino).orElseThrow(() -> new RuntimeException("Conta de destino não localizada"));
		
		Movimentacao novaMovimentacao = new Movimentacao(
				null,
				tipoMovimentacao,
				valorMovimentacao,
				dataHoraMovimentacao,
				idContaOrigem,
				idContaDestino,
				descricao);	
		
		MovimentacaoService.aplicarMovimentacao(contaOrigem, contaDestino, novaMovimentacao);
		
		contaRepository.salvar(contaOrigem);
		
		if(contaDestino != null) {
			contaRepository.salvar(contaDestino);
		}
	 
		movimentacaoRepository.salvar(novaMovimentacao);
	}	
}
