package Teste;



import application.usecase.MovimentacaoUseCase.AtualizarMovimentacaoUseCase;
import infrastructure.persistence.JpaContaRepository;
import infrastructure.persistence.JpaMovimentacaoRepository;
import infrastructure.persistence.JpaUtil;
import jakarta.persistence.EntityManagerFactory;

public class TesteUseCase {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		
		JpaMovimentacaoRepository repositoryMovimentacao = new JpaMovimentacaoRepository(emf);
		JpaContaRepository repositoryConta = new JpaContaRepository(emf);
		//MovimentacaoContaUseCase movimentacao = new MovimentacaoContaUseCase(repositoryConta, repositoryMovimentacao);
		//movimentacao.novaMovimentacao(TipoMovimentacao.TRANSFERENCIA, new BigDecimal(5000), null, 1L, 2L, "Teste");

		//CriarContaBancariaUseCase criarContaBancaria = new CriarContaBancariaUseCase(repositoryConta);
		//criarContaBancaria.novaContaBancaria("Banco SBB", "8888", new BigDecimal("10000"));
		
		//CriarContaInvestimentoUseCase criarContaInvestimento = new CriarContaInvestimentoUseCase(repositoryConta);
		//criarContaInvestimento.novaContaInvestimento("Banco Inter Invest", "5127", new BigDecimal("10000"));
		
		//DeletarContaUseCase deletarConta = new DeletarContaUseCase(repositoryConta, repositoryMovimentacao);
		//deletarConta.deletarConta(1L);
		
		//DeletarMovimentacaoUseCase deletarMovimentacao = new DeletarMovimentacaoUseCase(repositoryConta, repositoryMovimentacao);
		//deletarMovimentacao.deletarMovimentacao(1L);
		
		AtualizarMovimentacaoUseCase atualizarMovimentacao = new AtualizarMovimentacaoUseCase(repositoryConta, repositoryMovimentacao);
		atualizarMovimentacao.atualizarMovimentacao(1L, null, null, null, 2L, 1L, null);
	}
}
 	