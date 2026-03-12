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

		Conta conta = repositoryConta.BuscarPorId(movimentacao.getIdContaMovimentacao()).orElseThrow(() -> new RuntimeException("Conta não localizada."));
		
		MovimentacaoService.estornarMovimentacao(conta, movimentacao);
		
		repositoryConta.salvar(conta);
		
		repositoryMovimentacao.deletar(idMovimentacao);
	}
}
