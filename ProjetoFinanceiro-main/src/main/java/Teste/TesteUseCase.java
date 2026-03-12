package Teste;

import java.math.BigDecimal;

import application.usecase.MovimentacaoUseCase.MovimentacaoContaUseCase;
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
		
		MovimentacaoContaUseCase movimentacao = new MovimentacaoContaUseCase(repositoryConta, repositoryMovimentacao);
		movimentacao.novaMovimentacao(TipoMovimentacao.DESPESA, new BigDecimal(1000), null, 1L, "Teste");
		
		//TransferirContaUseCase transferir = new TransferirContaUseCase(repositoryConta, repositoryMovimentacao);
		//transferir.transferir(2L, 2L, new BigDecimal(10000), "Transferencia Teste2");

		//CriarContaBancariaUseCase criarContaBancaria = new CriarContaBancariaUseCase(repositoryConta);
		//criarContaBancaria.novaContaBancaria("Banco Santander", "7688", new BigDecimal("10000"));
		
		//CriarContaInvestimentoUseCase criarContaInvestimento = new CriarContaInvestimentoUseCase(repositoryConta);
		//criarContaInvestimento.novaContaInvestimento("Banco Inter Invest", "5127", new BigDecimal("10000"));
		
		//DeletarContaUseCase deletarConta = new DeletarContaUseCase(repositoryConta, repositoryMovimentacao);
		//deletarConta.deletarConta(2L);
		
		//DeletarMovimentacaoUseCase deletarDebito = new DeletarMovimentacaoUseCase(repositoryConta, repositoryMovimentacao);
		//deletarDebito.deletarDebito(3L);
	}

}
