package domain.movimentacao;

public enum TipoMovimentacao {
	
	RECEITA("RECEITA"),
	DESPESA("DESPESA"),
	
	TRANSFERENCIA("TRANSFERENCIA"),

	PAGAMENTO_CARTAO("PAGAMENTO CARTÃO"),
	COMPRA_CARTAO("COMPRA CARTÃO"),
	
	//Conta Investimento 
	COMPRA_ATIVO("COMPRA_ATIVO"),
	VENDA_ATIVO("VENDA_ATIVO");
	
	private final String descricao;
	
	TipoMovimentacao(String descricao){
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
