package Teste;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Scanner;

import domain.conta.Conta;
import domain.conta.ContaInvestimento;
import infrastructure.persistence.JpaContaInvestimentoRepository;
import infrastructure.persistence.JpaUtil;

public class TestePersistenciaContaBancaria {

	public static void main(String[] args) {
		
		JpaContaInvestimentoRepository persistencia = new JpaContaInvestimentoRepository(JpaUtil.getEntityManagerFactory());
			

		ContaInvestimento contaBancaria = new ContaInvestimento(null, LocalDateTime.now(), "Teste", "111", new BigDecimal("1000"));
		
		persistencia.salvar(contaBancaria);
		
		
		/*Optional<Conta> contaLocalizada = persistencia.BuscarPorId(1L);
		
		contaLocalizada.ifPresent(c ->
		System.out.println("Conta localizada: " + c.getId() + " " + c.getNomeBanco() + " " + c.getCodigoBanco() + " " + c.getDataCriacao() + " " + c.getSaldo())
				);
		
		persistencia.deletar(1L);

		JpaUtil.closeEntityManagerFactory();*/
	}
}
