package application.service;

import domain.conta.Conta;
import domain.movimentacao.Movimentacao;

public class MovimentacaoService {
	
	private MovimentacaoService() {}
	
	public static void aplicarMovimentacao(Conta conta, Movimentacao movimentacao) {
		
		switch(movimentacao.getTipoMovimentacao()) {
		case RECEITA:
			conta.creditar(movimentacao.getValorMovimentacao());
			break;
		case DESPESA:
			conta.debitar(movimentacao.getValorMovimentacao());
			break;
		default:
			throw new RuntimeException("Tipo movimentação inválido.");
		}
	}
	
	public static void estornarMovimentacao(Conta conta, Movimentacao movimentacao) {
		
		switch(movimentacao.getTipoMovimentacao()) {
		case RECEITA:
			conta.debitar(movimentacao.getValorMovimentacao());
			break;
		case DESPESA:
			conta.creditar(movimentacao.getValorMovimentacao());
			break;
		default:
			throw new RuntimeException("Tipo movimentacao inválida");
		}
	}
}
