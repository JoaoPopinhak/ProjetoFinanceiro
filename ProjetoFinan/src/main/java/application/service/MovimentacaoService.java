package application.service;

import domain.conta.Conta;
import domain.movimentacao.Movimentacao;

public class MovimentacaoService {
	
	private MovimentacaoService() {}
	
	public static void aplicarMovimentacao(Conta contaOrigem, Conta contaDestino, Movimentacao movimentacao) {
		
		switch(movimentacao.getTipoMovimentacao()) {
		case RECEITA:
			contaOrigem.creditar(movimentacao.getValorMovimentacao());
			break;
		case DESPESA:
			contaOrigem.debitar(movimentacao.getValorMovimentacao());
			break;
		case TRANSFERENCIA:
			contaOrigem.debitar(movimentacao.getValorMovimentacao());
			contaDestino.creditar(movimentacao.getValorMovimentacao());
			break;
		default:
			throw new RuntimeException("Tipo movimentação inválido.");
		}
		
		
	}
	
	public static void estornarMovimentacao(Conta contaOrigem, Conta contaDestino ,Movimentacao movimentacao) {
		
		switch(movimentacao.getTipoMovimentacao()) {
		case RECEITA:
			contaOrigem.debitar(movimentacao.getValorMovimentacao());
			break;
		case DESPESA:
			contaOrigem.creditar(movimentacao.getValorMovimentacao());
			break;
		case TRANSFERENCIA:
			contaOrigem.creditar(movimentacao.getValorMovimentacao());
			contaDestino.debitar(movimentacao.getValorMovimentacao());
			break;
		default:
			throw new RuntimeException("Tipo movimentacao inválida");
		}
	}
}
