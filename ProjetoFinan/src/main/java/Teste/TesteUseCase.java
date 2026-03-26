package Teste;



import application.usecase.MovimentacaoUseCase.AtualizarMovimentacaoUseCase;
import domain.movimentacao.TipoMovimentacao;
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
		//movimentacao.novaMovimentacao(TipoMovimentacao.RECEITA, new BigDecimal(1000), null, 1L, null, "Teste");

		//CriarContaBancariaUseCase criarContaBancaria = new CriarContaBancariaUseCase(repositoryConta);
		//criarContaBancaria.novaContaBancaria("Banco Santander", "7688", new BigDecimal("10000"));
		
		//CriarContaInvestimentoUseCase criarContaInvestimento = new CriarContaInvestimentoUseCase(repositoryConta);
		//criarContaInvestimento.novaContaInvestimento("Banco Inter Invest", "5127", new BigDecimal("10000"));
		
		//DeletarContaUseCase deletarConta = new DeletarContaUseCase(repositoryConta, repositoryMovimentacao);
		//deletarConta.deletarConta(1L);
		
		//DeletarMovimentacaoUseCase deletarMovimentacao = new DeletarMovimentacaoUseCase(repositoryConta, repositoryMovimentacao);
		//deletarMovimentacao.deletarMovimentacao(2L);
		
		AtualizarMovimentacaoUseCase atualizarMovimentacao = new AtualizarMovimentacaoUseCase(repositoryConta, repositoryMovimentacao);
		atualizarMovimentacao.atualizarMovimentacao(4L, TipoMovimentacao.DESPESA, null, null, null, null, null);
	}
}
 