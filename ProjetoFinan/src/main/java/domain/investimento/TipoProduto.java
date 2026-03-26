package domain.investimento;

public enum TipoProduto {
	
	RENDA_FIXA("Renda Fixa", true, false),
	POUPANCA("Poupança", false, false),
	ACOES("Ações", false, true),
	FIIS("FIIs", false,true),
	ETFS("ETFs", false,true);
	
	private final String descricaoTipo;
	private final boolean exigeVencimento;
	private final boolean exigeQuantidade;
	
	
	TipoProduto(String descricaoTipo, boolean exigeVencimento, boolean exigeQuantidade){
		this.descricaoTipo = descricaoTipo;
		this.exigeVencimento = exigeVencimento;
		this.exigeQuantidade = exigeQuantidade;
	}
	
	public String getDescricaoTipo() {
		return descricaoTipo;
	}
	
	public boolean exigeVencimento() {
		return exigeVencimento;
	}
	
	public boolean exigeQuantidade() {
		return exigeQuantidade;
	}
		
	
}
