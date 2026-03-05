package domain.conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 
 * @author joaom
 * @version 1.0
 * @see Conta
 */
public class ContaBancaria extends Conta{

	
	/**
	 * Construtor
	 * 
	 * @param dataCriacao
	 * @param nomeBanco
	 * @param codigoBanco
	 * @param saldoInicial
	 */
	public ContaBancaria(Long id,LocalDateTime dataCriacao, String nomeBanco, String codigoBanco, BigDecimal saldoInicial) {
		super(id, dataCriacao, nomeBanco, codigoBanco, saldoInicial);
	}

}
