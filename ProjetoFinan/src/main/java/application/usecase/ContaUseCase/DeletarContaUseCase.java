package application.usecase.ContaUseCase;

import application.repository.ContaRepository;
import application.repository.MovimentacaoRepository;
import domain.conta.Conta;

public class DeletarContaUseCase {
	
	private final ContaRepository repositoryConta;
	private final MovimentacaoRepository repositoryMovimentacao;
	
	public DeletarContaUseCase(ContaRepository repositoryConta, MovimentacaoRepository repositoryMovimentacao) {
		this.repositoryConta = repositoryConta;
		this.repositoryMovimentacao = repositoryMovimentacao;
	}
	
	
	public void deletarConta(Long idConta) {
		@SuppressWarnings("unused")
		Conta conta = repositoryConta.BuscarPorId(idConta).orElseThrow(() -> new RuntimeException("Conta não localizada"));
		
		repositoryMovimentacao.deletarPorConta(idConta);
		
		repositoryConta.deletar(idConta);	
	}
}
