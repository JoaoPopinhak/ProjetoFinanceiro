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
			            Long idConta, 
			            String descricao) {
		
		Conta conta = contaRepository.BuscarPorId(idConta).orElseThrow(() -> new RuntimeException("Conta não localizada"));
		
		if(dataHoraMovimentacao == null) {
			dataHoraMovimentacao = LocalDateTime.now();
		}
		
		Movimentacao novaMovimentacao = new Movimentacao(
				null,
				tipoMovimentacao,
				valorMovimentacao,
				dataHoraMovimentacao,
				idConta,
				descricao);	
		
		MovimentacaoService.aplicarMovimentacao(conta, novaMovimentacao);
		
		contaRepository.salvar(conta);
		
		movimentacaoRepository.salvar(novaMovimentacao);
	}	
}
