package domain.conta;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import domain.exception.ValorInvalidoException;
import domain.investimento.ProdutoInvestimento;
import domain.investimento.TipoProduto;


public class ContaInvestimento extends Conta{

	
	/**
	 * Construtor
	 * 
	 * @param dataCriacao
	 * @param nomeBanco
	 * @param codigoBanco
	 * @param saldoInicial
	 */
	public ContaInvestimento(Long id, LocalDateTime dataCriacao, String nomeBanco, String codigoBanco, BigDecimal saldoInicial) {
		super(id, dataCriacao, nomeBanco, codigoBanco, saldoInicial);
	}
	
	
	/**
	 * 
	 */
	public void compraInvestimento(
			TipoProduto tipoProduto,
			String nomeProduto,
			LocalDateTime dataVencimento,
			BigDecimal percentualCdi,
			BigDecimal valorUnitario,
			BigDecimal quantidadeCompra) {
		/*
		 * Criar o ProdutoInvestimento ok
		 * Calcular o valor total da compra ok
		 * Validar saldo da conta ok 
		 * Debitar saldo ok
		 * Verificar se já existe PosicaoInvestimento
		 * Criar nova posição ou comprar em posição existente
		 * Persistir posição (repositório / portfolio)
		 */
		
		ProdutoInvestimento produtoInvestimento = new ProdutoInvestimento(tipoProduto,nomeProduto,dataVencimento,percentualCdi,valorUnitario);
		
		BigDecimal valorCompra;
		
		if(produtoInvestimento.getTipoProduto().exigeQuantidade()) {
			if(quantidadeCompra == null || quantidadeCompra.compareTo(BigDecimal.ZERO) <= 0) {
				throw new ValorInvalidoException("Quantidade obrigatória para este tipo de investimento.");
			}
			
			valorCompra = quantidadeCompra.multiply(produtoInvestimento.getValorUnitario());
			
		}else {
			valorCompra = produtoInvestimento.getValorUnitario();
		}
		
		this.debitar(valorCompra);	
	}
}
