package Teste;

import java.math.BigDecimal;

import application.usecase.CriarContaBancariaUseCase;
import domain.conta.ContaBancaria;
import infrastructure.persistence.JpaContaBancariaRepository;
import infrastructure.persistence.JpaUtil;
import jakarta.persistence.EntityManagerFactory;



public class TesteUseCaseContaBancaria {

	public static void main(String[] args) {
		
		EntityManagerFactory emf = JpaUtil.getEntityManagerFactory();
		
		JpaContaBancariaRepository repository = new JpaContaBancariaRepository(emf);
		
		CriarContaBancariaUseCase useCase = new CriarContaBancariaUseCase(repository);
		
		ContaBancaria contaCriada = useCase.novaContaBancaria("Banco BTG Pactual", "666", new BigDecimal("10000"));
		
		System.out.println("ID: " + contaCriada.getId() + "\n" + 
		                   "Nome conta: " + contaCriada.getNomeBanco() + "\n" + 
				           "Data de criação: " + contaCriada.getDataCriacao() + "\n" + 
		                   "Codigo do Banco: " + contaCriada.getCodigoBanco() + "\n" + 
				           "Saldo: " + contaCriada.getSaldo());

		emf.close();
	}

}
