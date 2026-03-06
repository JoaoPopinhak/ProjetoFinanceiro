package application.usecase;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import application.repository.ContaRepository;
import application.repository.MovimentacaoRepository;
import domain.conta.Conta;
import domain.movimentacao.Movimentacao;
import domain.movimentacao.TipoMovimentacao;

public class TransferirContaUseCase {
	
	private ContaRepository contaRepository;
	private MovimentacaoRepository movimentacaoRepository;
	
	public TransferirContaUseCase(ContaRepository contaRepository, MovimentacaoRepository movimentacaoRepository) {
		this.contaRepository = contaRepository;
		this.movimentacaoRepository = movimentacaoRepository;
	}
	
	public void Transferir (Long idContaOrigem, Long idContaDestino, BigDecimal valorTransferencia, String descricao) {
		
		Conta contaOrigem = contaRepository.BuscarPorId(idContaOrigem).orElseThrow(() -> new RuntimeException("Conta origem não localizado"));
		
		Conta contaDestino = contaRepository.BuscarPorId(idContaDestino).orElseThrow(() -> new RuntimeException("Conta destino não localizado"));
		
		contaOrigem.debitar(valorTransferencia);
		
		contaDestino.creditar(valorTransferencia);
		
		Movimentacao transferenciaEnvio = new Movimentacao(
				null,
				TipoMovimentacao.TRANSFERENCIA_ENVIO,
				valorTransferencia,
				LocalDateTime.now(),
				contaOrigem.getId(),
				descricao); 
		
		Movimentacao transferenciaRecebimento = new Movimentacao(
				null,
				TipoMovimentacao.TRANSFERENCIA_RECEBIMENTO,
				valorTransferencia,
				LocalDateTime.now(),
				contaDestino.getId(),
				descricao);
		
		movimentacaoRepository.salvar(transferenciaEnvio);
		
		movimentacaoRepository.salvar(transferenciaRecebimento);
		
		contaRepository.salvar(contaOrigem);
		
		contaRepository.salvar(contaDestino);
	
	}
}
