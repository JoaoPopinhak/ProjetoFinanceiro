package application.usecase.MovimentacaoUseCase;

import application.repository.ContaRepository;
import application.repository.MovimentacaoRepository;
import application.service.MovimentacaoService;
import domain.conta.Conta;
import domain.movimentacao.Movimentacao;


public class DeletarMovimentacaoUseCase {
	
	private ContaRepository repositoryConta;
	private MovimentacaoRepository repositoryMovimentacao;
	
	public DeletarMovimentacaoUseCase(ContaRepository repositoryConta, MovimentacaoRepository repositoryMovimentacao) {
		this.repositoryConta = repositoryConta;
		this.repositoryMovimentacao = repositoryMovimentacao;
	}
	
	public void deletarMovimentacao(Long idMovimentacao) {
		
		Movimentacao movimentacao = repositoryMovimentacao.buscarPorId(idMovimentacao).orElseThrow(() -> new RuntimeException("Movimentação não localizada."));

		Conta contaOrigem = repositoryConta.BuscarPorId(movimentacao.getIdContaOrigem()).orElseThrow(() -> new RuntimeException("Conta de origem não localizada."));
		
		Conta contaDestino = movimentacao.getIdContaDestino() == null ? null : repositoryConta.BuscarPorId(movimentacao.getIdContaDestino()).orElseThrow(() -> new RuntimeException("Conta de destino não localizada"));
		
		MovimentacaoService.estornarMovimentacao(contaOrigem, contaDestino ,movimentacao);
		
		
		repositoryConta.salvar(contaOrigem);
		
		if(contaDestino != null) {
			repositoryConta.salvar(contaDestino);
		}
		
		repositoryMovimentacao.deletar(idMovimentacao);
	}
}
